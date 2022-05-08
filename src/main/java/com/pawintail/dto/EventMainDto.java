package com.pawintail.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class EventMainDto {

	private Long eventId;
	
	private String eventName;
	
	private String imgUrl;
	
	private List<EventImgDto> eventImgDtoList = new ArrayList<>(); 
	private List<Long> eventImgIds = new ArrayList<Long>();

	private static ModelMapper modelMapper = new ModelMapper();
	
	//QEventMainDto Q테이블 생성
	@QueryProjection
    public EventMainDto(Long id, String eventName, String imgUrl){
		this.eventId = eventId;
		this.eventName = eventName;
		this.imgUrl = this.imgUrl;
	}
       
}
