package com.fon.footballfantasystatsservice.domain.event;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fon.footballfantasystatsservice.domain.Club;

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
public abstract class MatchEvent implements Serializable {

	private static final long serialVersionUID = 5506326865651812068L;
	
	private Long matchId;
	private String minute;
	private String result;
	private Club club;
	
}
