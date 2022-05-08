package com.pawintail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pawintail.entity.EventImg;
import com.pawintail.entity.ItemImg;

public interface EventImgRepository extends JpaRepository<EventImg, Long>{

	
	@Query("Select e from EventImg e where e.event.id = :eventId order by e.event.id asc ")
	List<EventImg> findByEventOrderByIdAsc(@Param(value = "eventId") Long eventId);

	@Query("Select e, ev from EventImg e join Event ev on e.event = ev order by e.event.id asc ")
	List<Object[]> findByEventIdOrderByIdAsc();
	//delete from event_img where event_id = 1;
	//이벤트 밑에 딸린 이벤트 이미지 삭제
	
	@Modifying
	@Query("delete from EventImg ei where ei.event.eventId =:eventId ")
    public void deleteByEventId(@Param("eventId") Long eventId);
	
	
}
