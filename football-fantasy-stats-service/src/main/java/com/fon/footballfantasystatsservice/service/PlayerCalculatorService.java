package com.fon.footballfantasystatsservice.service;

import javax.validation.constraints.NotNull;

import com.fon.footballfantasystatsservice.domain.Player;
import com.fon.footballfantasystatsservice.domain.PlayerGameweekPerformance;

public interface PlayerCalculatorService {
	
	double getBasePrice(@NotNull Player player);
	
	double getUpdatedPrice(@NotNull PlayerGameweekPerformance pgp);

}
