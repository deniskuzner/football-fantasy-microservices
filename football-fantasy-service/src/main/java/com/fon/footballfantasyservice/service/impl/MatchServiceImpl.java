package com.fon.footballfantasyservice.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyservice.domain.Match;
import com.fon.footballfantasyservice.exception.MatchException;
import com.fon.footballfantasyservice.exception.MatchException.MatchExceptionCode;
import com.fon.footballfantasyservice.repository.MatchRepository;
import com.fon.footballfantasyservice.service.ClubService;
import com.fon.footballfantasyservice.service.MatchService;
import com.fon.footballfantasyservice.service.dto.MatchSearchRequest;

@Service
@Transactional
@Validated
public class MatchServiceImpl implements MatchService {
	
	@Autowired
	MatchRepository matchRepository;
	
	@Autowired
	ClubService clubService;

	@Override
	public Match save(Match match) {
		System.out.println(match.getHost());
		System.out.println(match.getGuest());
		String hostUrl = match.getHost().getUrl();
		String guestUrl = match.getGuest().getUrl();
		// Postavljanje klubova
		if(match.getHost().getId() == null || match.getGuest().getId() == null)
			setClubs(match);
		
		// Provera da li vec postoji mec u bazi
		Match m = findMatch(match);
		if(m != null) {
			match.setId(m.getId());
			match.setCreatedOn(m.getCreatedOn());
		}
		
		try {
			matchRepository.save(match);
		} catch (DataIntegrityViolationException e) {
			throw new MatchException(MatchExceptionCode.CLUBS_NOT_FOUND,
				"MATCH %s clubs not found! MATCH DATE: %s, HOST URL: %s, GUEST URL: %s", 
				match.getUrl() == null ? "" : "URL: " + match.getUrl(), match.getDateTime(), hostUrl, guestUrl);
		}
		
		return match;
	}

	@Override
	public Match findById(Long id) {
		return matchRepository.findById(id).get();
	}
	
	@Override
	public Match findByUrl(String url) {
		return matchRepository.findByUrl(url);
	}

	@Override
	public List<Match> findAll() {
		return (List<Match>) matchRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		matchRepository.deleteById(id);
	}
	
	private void setClubs(Match match) {
		match.setHost(clubService.findByUrl(match.getHost().getUrl()));
		match.setGuest(clubService.findByUrl(match.getGuest().getUrl()));
	}
	
	private Match findMatch(Match match) {
		return matchRepository.findByHostAndGuestAndGameweek(match.getHost(), match.getGuest(), match.getGameweek());
	}

	@Override
	public List<Match> searchMatches(MatchSearchRequest matchSearchRequest) {
		LocalDateTime fromDate = matchSearchRequest.getFromDate().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
		LocalDateTime toDate = matchSearchRequest.getToDate().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
		return matchRepository.findBySentAndDateTimeBetween(false, fromDate, toDate);
	}

	@Override
	public List<Match> findByGameweekId(@NotNull @Min(1) Long gameweekId) {
		return matchRepository.findBySentAndGameweekId(false, gameweekId);
	}

	@Override
	public int updateSent(@NotNull @Min(1) Long id) {
		return matchRepository.updateSent(id);
	}

	@Override
	public List<Match> findByGameweekOrderNumber(int gameweekOrderNumber) {
		return matchRepository.findByGameweekOrderNumber(gameweekOrderNumber);
	}

}
