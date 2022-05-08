package com.pawintail.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawintail.dto.EventFormDto;
import com.pawintail.dto.EventMainDto;
import com.pawintail.dto.EventSearchDto;
import com.pawintail.dto.ItemFormDto;
import com.pawintail.dto.ItemSearchDto;
import com.pawintail.dto.MainItemDto;
import com.pawintail.entity.Event;
import com.pawintail.entity.Item;
import com.pawintail.service.EventService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class EventController {
	
	private final EventService eventService;

	//전체유저 이벤트 전체 보기
		@GetMapping("/event/eventList")
		public String eventMain(Optional<Integer> page, Model model) {
			 Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 9);
		    
			 Page<EventMainDto> events = eventService.getEventMainPage(pageable);

		        model.addAttribute("events", events);
		        //model.addAttribute("eventSearchDto", eventSearchDto);
		        model.addAttribute("maxPage", 5);
		        System.out.println("Event목록 List.................");
				events.getContent().forEach(log::info);
		        return "event/eventList";
			
			/*
			Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 9);
			//EventSearchDto eventSearchDto = new EventSearchDto();
	        Page<EventMainDto> events = eventService.getEventMainPage(pageable);

	        model.addAttribute("events", events);
	     //   model.addAttribute("eventsDto", eventSearchDto);
	        model.addAttribute("maxPage", 5);
			System.out.println("Event목록 List.................");
			events.getContent().forEach(log::info);
			return "event/eventList";*/
		}

//	@GetMapping("/event/eventList")
//	public String eventMain(EventSearchDto eventSearchDto, Optional<Integer> page, Model model) {
//		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 9);
//        Page<EventMainDto> events = eventService.getEventMainPage(eventSearchDto, pageable);
//
//        model.addAttribute("events", events);
//        model.addAttribute("eventsDto", eventSearchDto);
//        model.addAttribute("maxPage", 5);
//		System.out.println("Event목록 List.................");
//		events.getContent().forEach(log::info);
//		return "event/eventList";
//	}
	//관리자이벤트신규등록페이지 호출 get
	@GetMapping("/admin/event/new")
	public String eventForm(Model model) {
		model.addAttribute("eventFormDto", new EventFormDto() );
		return "event/eventForm";
	}
		
	//관리자 이벤트 신규 등록 post
	@PostMapping("/admin/event/new")
	public String eventNew(@Valid  EventFormDto eventFormDto, 
			BindingResult bindingResult, Model model,
			@RequestParam("eventImgFile") List<MultipartFile> eventImgFileList) throws Exception
	{
		
		if(bindingResult.hasErrors()) {
			return "event/eventForm";
		}
		if(eventImgFileList.get(0).isEmpty() && 
				eventFormDto.getEventId() == null) {
			model.addAttribute("errorMessage", "첫번째 이벤트 이미지는 필수 입력 값입니다.");
			return "event/eventForm";	
		}
		try {
			eventService.saveEvent(eventFormDto, eventImgFileList);
		} catch (IOException e) {
			model.addAttribute("errorMessage", "이벤트 등록 중 에러가 발생하였습니다.");
			return "event/eventForm";
		}		
		return "event/eventList";		
	}	
		
	//관리자 이벤트 전체 페이지 호출
	/*
	@GetMapping(value = {"/admin/events", "/admin/events/{page}"})
    public String eventManage(@PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<EventMainDto> events = eventService.getEventMainPage(pageable);

        model.addAttribute("events", events);
        //model.addAttribute("eventSearchDto", eventSearchDto);
        model.addAttribute("maxPage", 5);

        return "event/eventManage";
    }*/
	
	//이벤트 상세페이지 호출 get
	@GetMapping("/event/detail")
	public String eventDetail(Model model) {
		model.addAttribute("eventFormDto", new EventFormDto() );
		return "event/eventDetail";
	}

	//관리자 이벤트 삭제	
	/*
	@PostMapping("admin/event/remove")
	public String removeEvent(long eventId, RedirectAttributes redirectAttributes) {
		System.out.println("삭제 eventId: "+eventId);
		eventService.removeWithEventImgs(eventId);
		redirectAttributes.addFlashAttribute("msg", eventId);
		return "admin/events";
	}
	*/
}
