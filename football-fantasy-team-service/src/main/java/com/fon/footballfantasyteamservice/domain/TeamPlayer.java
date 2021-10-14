package com.fon.footballfantasyteamservice.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fon.footballfantasyteamservice.domain.dto.Player;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "team_players")
public class TeamPlayer implements Serializable {
	
	private static final long serialVersionUID = -7851390135673911944L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	@Column(name = "modified_on")
	private LocalDateTime modifiedOn;
	
	private int points;
	@Column(name = "on_bench")
	private boolean onBench;
	@Column(name = "player_id")
	private Long playerId;
	@Transient
	private Player player;
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

}
