package com.pawintail.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.pawintail.constant.ItemSellStatus;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemDto {

	private Long id;

    private String itemName;
    private String itemDetail;
    private String imgUrl;
    private Integer price;
    //private ItemSellStatus itemSellStatus;
    
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>(); 
	private List<Long> itemImgIds = new ArrayList<Long>();

	private static ModelMapper modelMapper = new ModelMapper();
	
	//QMainItemDto Q테이블 생성
    @QueryProjection
    public MainItemDto(Long id, String itemName, String itemDetail, String imgUrl,Integer price){ //ItemSellStatus itemSellStatus
        this.id = id;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
        //this.itemSellStatus = itemSellStatus;
    }
}
