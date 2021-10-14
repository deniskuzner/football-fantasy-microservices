package com.fon.footballfantasyservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fon.footballfantasyservice.domain.Club;

public interface ClubRepository extends CrudRepository<Club, Long> {
	
	Club findByUrl(String url);

	@Query(value = "select name from clubs", nativeQuery = true)
	List<String> findAllNames();

}
