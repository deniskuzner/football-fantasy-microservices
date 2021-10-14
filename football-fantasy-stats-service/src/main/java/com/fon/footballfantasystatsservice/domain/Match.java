package com.fon.footballfantasystatsservice.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fon.footballfantasystatsservice.domain.event.MatchEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match implements Serializable {

	private static final long serialVersionUID = 9089264137411582677L;
	
	private Long id;
	private String url;
	private String result;
	private LocalDateTime dateTime;
	private String venue;
	private Club host;
	private Club guest;
	@JsonIgnoreProperties(value = "matches", allowSetters = true)
	private Gameweek gameweek;
	private List<MatchEvent> events;
	private boolean sent;
	
}
