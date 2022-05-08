package com.pawintail.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

//import com.pawintail.dto.ItemAllDto;
import com.pawintail.dto.ItemFormDto;
import com.pawintail.dto.ItemImgDto;
import com.pawintail.dto.ItemSearchDto;
import com.pawintail.dto.MainItemDto;
import com.pawintail.entity.Item;
import com.pawintail.entity.ItemImg;
import com.pawintail.repository.ItemImgRepository;
import com.pawintail.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService{

	private final ItemRepository itemRepository;
	private final ItemImgRepository itemImgRepository;
	private final ItemImgService itemImgService;
	
	//아이템 등록
	public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{

        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }
	
	
	@Transactional(readOnly = true)
	public ItemFormDto getItemDetail(Long itemId) {
    	List<ItemImg> itemImgList = itemImgRepository.findByItemOrderByIdAsc(itemId);
    	List<ItemImgDto> itemDtoList = new ArrayList<ItemImgDto>();
    	
    	for(ItemImg itemImg : itemImgList)
    	{
    		ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
    		itemDtoList.add(itemImgDto);
    	}
    	Item item = itemRepository.findById(itemId).orElseThrow(
    			EntityNotFoundException::new);
    	ItemFormDto itemFormDto = ItemFormDto.of(item);
    	itemFormDto.setItemImgDtoList(itemDtoList);
    	
		return itemFormDto;
	}
	
	
	@Transactional(readOnly = true)
	public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable)
	{
		return itemRepository.getMainItemPage(itemSearchDto, pageable);
	
	}
	
	
	@Transactional(readOnly = true)
	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable)
	{
		return itemRepository.getAdminItemPage(itemSearchDto, pageable);
	}
	
	
	public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemFileList) throws Exception
    {
    	Item item = itemRepository.findById(itemFormDto.getId())
    			.orElseThrow(EntityNotFoundException::new);
    	item.updateItem(itemFormDto);
    	List<Long> itemImgIds = itemFormDto.getItemImgIds();
    	for(int i = 0 ; i < itemFileList.size();i ++) {
    		itemImgService.updateItemImg(itemImgIds.get(i), itemFileList.get(i));
    	}
		return item.getId();
        
    }
    
	
	
}
