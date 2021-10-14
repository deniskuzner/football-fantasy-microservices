package com.fon.footballfantasystatsservice.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Club implements Serializable {
	
	private static final long serialVersionUID = 7705085158586004971L;
	
	private Long id;
	private String url;
	private String name;
	private String image;
	private String manager;
	@JsonIgnoreProperties(value = "club", allowSetters = true)
	private List<Player> players;
	
}
