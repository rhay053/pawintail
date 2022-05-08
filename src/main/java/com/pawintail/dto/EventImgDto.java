package com.pawintail.dto;

import org.modelmapper.ModelMapper;

import com.pawintail.entity.EventImg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class EventImgDto {

	private Long eventId;
	
	private String eventImgName; //이미지 파일명

    private String eventOriImgName; //원본 이미지 파일명

    private String eventImgUrl; //이미지 조회 경로

    private String eventRepImgYn; //대표 이미지 여부
    
    private static ModelMapper modelMapper = new ModelMapper();
    
    public static  EventImgDto of(EventImg eventImg) {
		return modelMapper.map(eventImg, EventImgDto.class);
	}
    
}
