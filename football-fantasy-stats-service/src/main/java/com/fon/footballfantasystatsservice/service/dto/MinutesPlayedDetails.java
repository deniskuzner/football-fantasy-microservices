package com.fon.footballfantasystatsservice.service.dto;

import java.util.Optional;

import com.fon.footballfantasystatsservice.domain.Club;
import com.fon.footballfantasystatsservice.domain.Match;
import com.fon.footballfantasystatsservice.domain.event.Substitution;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class MinutesPlayedDetails {
	
	private int minuteIn;
	private int minuteOut;
	private Optional<Substitution> inSubstitution;
	private Optional<Substitution> outSubstitution;
	
	public boolean isHost(Match match, Club club) {
		return club.getId().equals(match.getHost().getId());
	}

	public boolean isGuest(Match match, Club club) {
		return club.getId().equals(match.getGuest().getId());
	}
	
	public boolean isCleanSheetCandidate() {
		if(minuteOut - minuteIn >= 60) {
			return true;
		}
		return false;
	}

}
