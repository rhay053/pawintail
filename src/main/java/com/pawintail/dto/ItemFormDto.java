package com.pawintail.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.pawintail.constant.ItemSellStatus;
import com.pawintail.entity.Item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemFormDto {

	private Long id;
	
	@NotBlank(message = "상품명은 필수 입력 값입니다.")
	private String itemName;
	
	@NotNull(message = "무게는 필수 입력 값입니다.")
	private Integer itemWeight;
	
	@NotNull(message = "가격은 필수 입력 값입니다.")
	private Integer price;
	
	@NotNull(message = "재고는 필수 입력 값입니다.")
	private Integer stockNumber;
	
	@NotBlank(message = "설명은 필수 입력 값입니다.")
	private String itemDetail;
	
	@NotBlank(message = "새벽배송여부는 필수 입력 값입니다.")
	private String dawnDelYn; //새벽배송여부 "Y/N"
		
	private ItemSellStatus itemSellStatus; 
	
	@NotBlank(message = "대상동물은 필수 입력 값입니다.")
	private String petType;
	
	@NotBlank(message = "식품유형 필수 입력 값입니다.")
	private String foodType;
	
	private List<ItemImgDto> itemImgDtoList = new ArrayList<>(); 
	private List<Long> itemImgIds = new ArrayList<Long>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Item createItem() {
		return modelMapper.map(this, Item.class);
	}
	public static ItemFormDto of(Item item) {
		return modelMapper.map(item, ItemFormDto.class);
	}

}
