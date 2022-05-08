package com.pawintail.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pawintail.constant.ItemSellStatus;
import com.pawintail.dto.ItemFormDto;
import com.pawintail.entity.Item;
import com.pawintail.repository.ItemImgRepository;
import com.pawintail.repository.ItemRepository;

@SpringBootTest
@Transactional
public class ItemServiceTest {

	
	@Autowired
	ItemService itemService;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	ItemImgRepository itemImgRepository;
	
	
	/*
	List<MultipartFile> createMultipartFile() {
		List<MultipartFile> multipartFileList = new ArrayList<MultipartFile>();
		for(int i = 0; i < 5; i++) {
			String path ="c:/KOSTA/pawintail/item";
			String imageName = "image"+i+".jpg";
			MockMultipartFile multipartFile =
					new MockMultipartFile(path, imageName, 
							"image/jpg",new byte[] {1,2,3,4});
			multipartFileList.add(multipartFile);
		}
		return multipartFileList;
	}*/
	
	/*
	@Test
	@DisplayName("상품 등록 테스트")
	@WithMockUser(username = "admin", roles="ADMIN")
	@Commit
	void saveItem() throws Exception {
		ItemFormDto itemFormDto =new ItemFormDto();
			itemFormDto.setItemName("테스트상품");
			itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
			itemFormDto.setItemDetail("테스트 상품 입니다.");
			itemFormDto.setPrice(10000);
			itemFormDto.setStockNumber(10);
			itemFormDto.setDawnDelYn("N");
			itemFormDto.setItemWeight(3);
			itemFormDto.setFoodType("간식");
			itemFormDto.setPetType("고양이");
			
		List<MultipartFile> multipartFileList = this.createMultipartFile();
		Long itemId = itemService.saveItem(itemFormDto, multipartFileList);
    
		Item item = itemRepository.findById(itemId).orElseThrow(
				EntityNotFoundException::new);
		System.out.println(itemFormDto.getItemName());
		System.out.println(itemFormDto.getItemDetail());
	}	
	*/
	
	//부모자식레코드 서비스-removeWithItemImg로 일일히 삭제
	/*
	@Test
	@DisplayName("아이템 삭제 테스트")
	@WithMockUser(username = "admin", roles="ADMIN")
	@Transactional
	@Commit
	void removeItem() throws Exception {
		
	   Long	itemId = 81L;
		
		System.out.println("삭제 itemId: "+itemId);
		itemService.removeWithItemImgs(itemId);
	
		
	}
	*/
	
	//@OneToMany추가 cascade로 부모레코드item삭제시 자식레코드까지삭제
	/*
	@Test
	@DisplayName("아이템 삭제 테스트")
	@WithMockUser(username = "admin", roles="ADMIN")
	@Transactional
	@Commit
	void removeItem() throws Exception {
		
		Long itemId = 21L;
		
		System.out.println("삭제 itemId: "+itemId);
	    itemRepository.deleteById(itemId);	
		
	}
	*/
}
