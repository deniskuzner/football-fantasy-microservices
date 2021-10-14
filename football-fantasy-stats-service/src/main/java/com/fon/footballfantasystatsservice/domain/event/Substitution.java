package com.fon.footballfantasystatsservice.domain.event;

import com.fon.footballfantasystatsservice.domain.Player;

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
public class Substitution extends MatchEvent {
	
	private static final long serialVersionUID = 1410704224587036385L;
	
	private Player inPlayer;
	private Player outPlayer;

}
