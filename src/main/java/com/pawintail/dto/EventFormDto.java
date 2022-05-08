package com.pawintail.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.pawintail.entity.Event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class EventFormDto {

	private Long eventId;
	
	@NotBlank(message = "이벤트명은 필수 입력 값입니다.")
	private String eventName;
	
	private List<EventImgDto> eventImgDtoList = new ArrayList<>(); 
	private List<Long> eventImgIds = new ArrayList<Long>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Event createEvent() {
		
		return modelMapper.map(this, Event.class);
	}
	public static EventFormDto of(Event event) {
		return modelMapper.map(event, EventFormDto.class);
	}
	
}
