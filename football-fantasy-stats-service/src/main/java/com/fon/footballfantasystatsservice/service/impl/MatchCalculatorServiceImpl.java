package com.fon.footballfantasystatsservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasystatsservice.calculator.MatchPerformanceCalculator;
import com.fon.footballfantasystatsservice.domain.Match;
import com.fon.footballfantasystatsservice.domain.PlayerGameweekPerformance;
import com.fon.footballfantasystatsservice.service.MatchCalculatorService;

@Service
@Validated
public class MatchCalculatorServiceImpl implements MatchCalculatorService {
	
	@Autowired
	MatchPerformanceCalculator matchPerformanceCalculator;

	@Override
	public List<PlayerGameweekPerformance> getMatchPerformances(Match match) {
		return matchPerformanceCalculator.getMatchPerformances(match);
	}

}
