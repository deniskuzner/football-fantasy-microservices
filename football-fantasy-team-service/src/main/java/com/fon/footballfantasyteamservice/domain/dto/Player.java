package com.fon.footballfantasyteamservice.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class Player implements Serializable {
	
	private static final long serialVersionUID = -7725311564610239522L;
	
	private Long id;
	private String url;
	private String name;
	private String nationality;
	private String birthDate;
	private String age;
	private String position;
	private String height;
	private String weight;
	private String image;
	private double price;
	private int totalPoints;
	@JsonIgnoreProperties(value = "players", allowSetters = true)
	private Club club;
	@JsonIgnoreProperties(value = "player", allowSetters = true)
	private List<PlayerGameweekPerformance> playerGameweekPerformances;
	
}
