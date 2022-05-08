package com.pawintail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawintail.dto.EventMainDto;
import com.pawintail.dto.EventSearchDto;
import com.pawintail.entity.Event;

public interface EventRepositoryCustom {

	Page<Event> getAdminEventMainPage(EventSearchDto eventSearchDto, Pageable pageable);
	
	Page<EventMainDto> getEventMainPage(EventSearchDto eventSearchDto, Pageable pageable);
}
