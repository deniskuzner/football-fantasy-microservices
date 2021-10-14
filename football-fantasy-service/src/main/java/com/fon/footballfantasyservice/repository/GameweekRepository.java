package com.fon.footballfantasyservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fon.footballfantasyservice.domain.Gameweek;

public interface GameweekRepository extends CrudRepository<Gameweek, Long> {
	
	Gameweek findByOrderNumber(int orderNumber);
	
	@Query(value = "SELECT gameweek_id FROM matches WHERE date_time < NOW() ORDER BY date_time DESC LIMIT 1", nativeQuery = true)
	Long findCurrentGameweekId();
	
	@Query(value = "SELECT order_number FROM gameweeks WHERE id = :id", nativeQuery = true)
	int findOrderNumberById(@Param("id") Long id);
	
	@Query(value = "SELECT id FROM gameweeks WHERE order_number = :orderNumber", nativeQuery = true)
	Long findIdByOrderNumber(@Param("orderNumber") int orderNumber);
	
}
