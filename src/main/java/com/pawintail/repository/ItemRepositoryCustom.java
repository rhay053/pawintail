package com.pawintail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pawintail.dto.ItemSearchDto;
import com.pawintail.dto.MainItemDto;
import com.pawintail.entity.Item;

public interface ItemRepositoryCustom {

	Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
	
	Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
