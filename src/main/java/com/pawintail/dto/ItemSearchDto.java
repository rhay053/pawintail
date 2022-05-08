package com.pawintail.dto;

import com.pawintail.constant.ItemSellStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

	//아이템 목록 페이징시 주소창 문자열에 대입될 변수? 지정
	private String searchDateType;
	private ItemSellStatus searchSellStatus;
	private String searchBy;
	private String searchQuery="";
	
}
