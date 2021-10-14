package com.fon.footballfantasystatsservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasystatsservice.calculator.PlayerPriceCalculator;
import com.fon.footballfantasystatsservice.domain.Player;
import com.fon.footballfantasystatsservice.domain.PlayerGameweekPerformance;
import com.fon.footballfantasystatsservice.service.PlayerCalculatorService;

@Service
@Validated
public class PlayerCalculatorServiceImpl implements PlayerCalculatorService {

	@Autowired
	PlayerPriceCalculator playerPriceCalculator;
	
	@Override
	public double getBasePrice(Player player) {
		return playerPriceCalculator.getBasePrice(player);
	}

	@Override
	public double getUpdatedPrice(PlayerGameweekPerformance pgp) {
		return playerPriceCalculator.getUpdatedPrice(pgp);
	}

}
