package com.pawintail.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.pawintail.dto.EventMainDto;
import com.pawintail.entity.Event;


public interface EventRepository extends JpaRepository<Event, Long>{

	@Query("select e from Event e where e.eventId = :eventId")
	Event findByEventId(@Param(value="eventId") Long eventId);
    
	
	}
