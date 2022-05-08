package com.pawintail.dto;

import com.pawintail.constant.ItemSellStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EventSearchDto {

	private String searchDateType;
	private String searchBy;
	private String searchQuery="";
	
}
