package com.fon.footballfantasyparserservice.domain.event;

import com.fon.footballfantasyparserservice.domain.Player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class Card extends MatchEvent {

	private static final long serialVersionUID = 7192953543393167229L;
	
	private Player player;
	private String card;

}
