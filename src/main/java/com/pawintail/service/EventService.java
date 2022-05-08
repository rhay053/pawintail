package com.pawintail.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.pawintail.dto.EventFormDto;
import com.pawintail.dto.EventMainDto;
import com.pawintail.dto.EventSearchDto;
import com.pawintail.dto.ItemFormDto;
import com.pawintail.entity.Event;
import com.pawintail.entity.EventImg;
import com.pawintail.entity.Item;
import com.pawintail.entity.ItemImg;
import com.pawintail.repository.EventImgRepository;
import com.pawintail.repository.EventRepository;
import com.pawintail.repository.EventRepositoryCustom;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class EventService{

	private final EventRepository eventRepository;
	private final EventImgRepository eventImgRepository; 
	private final EventImgService eventImgService;
	
	//수정할때 eventService를 인터페이스, 상세실행내용을 eventServiceImpl클래스로 나누기
	//이벤트 등록
	public Long saveEvent(EventFormDto eventFormDto,  
		List<MultipartFile> eventImgFileList) throws Exception {
		log.info(eventFormDto);
		Event event = eventFormDto.createEvent();
        log.info("===========================저장 이벤트 ========================");
		log.info(event);		
		eventRepository.save(event);
		
		for(int i= 0; i < eventImgFileList.size(); i++) {
			System.out.println("saveEventImg===================================="+eventImgFileList.get(i).getOriginalFilename());
			EventImg eventImg = new EventImg();
				eventImg.setEvent(event);
				if(i == 0) {
					eventImg.setEventRepImgYn("Y");
				} else { 
					eventImg.setEventRepImgYn("N");}
				eventImgService.saveEventImg(eventImg, eventImgFileList.get(i));

		}
		return event.getEventId();
		
	}
	
	@Transactional(readOnly=true)
    public Page<EventMainDto> getEventMainPage(Pageable pageable)
	{
		List<Object[]> eventImgs = eventImgRepository.findByEventIdOrderByIdAsc();
		List<EventMainDto> eventMainDtos = 
				eventImgs.stream().map((Function<Object[], EventMainDto>)e->{
			EventImg eventImg = (EventImg)e[0];
			Event event = (Event)e[1];
			return new EventMainDto(eventImg.getId(), event.getEventName(), eventImg.getEventImgUrl());
		}).collect(Collectors.toList());
		
		return new PageImpl<EventMainDto>(eventMainDtos, pageable, eventMainDtos.size()); 
	
	}
	
//	@Transactional(readOnly = true)
//	public Page<EventMainDto> getEventMainPage(Pageable pageable)
//	{
//		return eventRepository.getEventMainPage(pageable);
//	
//	
//	
//	
//	@Transactional(readOnly = true)
//	public Page<EventMainDto> getAdminEventPage(Pageable pageable)
//	{
//		return eventRepository.getAdminEventMainPage(pageable);
//	
//	}
//	
//	//이벤트수정
	public Long updateItem(EventFormDto eventFormDto, List<MultipartFile> eventFileList) throws Exception
    {
    	Event event = eventRepository.findByEventId(eventFormDto.getEventId());
    			//.orElseThrow(EntityNotFoundException::new);
    	event.updateEvent(eventFormDto);
    	List<Long> eventImgIds = eventFormDto.getEventImgIds();
    	for(int i = 0 ; i < eventFileList.size();i ++) {
    		eventImgService.updateEventImg(eventImgIds.get(i), eventFileList.get(i));
    	}
		return event.getEventId();
        
    }
	
	//이벤트이미지, 이벤트 삭제
	@Transactional
	public void removeWithEventImgs(Long eventId) {
		eventImgRepository.deleteByEventId(eventId); //eventId로 이벤트이미지들 삭제하는 쿼리로 삭제
		eventRepository.deleteById(eventId); //이벤트를 삭제
	}
	
}
