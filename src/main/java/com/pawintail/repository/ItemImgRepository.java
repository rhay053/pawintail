package com.pawintail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pawintail.entity.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

	//아이템아이디로 아이템이미지 가져와서 오름차정렬, 화면에 아이템상세페이지의 아이템 이미지 가져옴
	
	@Query("Select i from ItemImg i where i.item.id = :itemId order by i.item.id asc ")
	List<ItemImg> findByItemOrderByIdAsc(@Param(value = "itemId") Long itemId);
	
	@Query("Select i from ItemImg i where i.item.id = :itemId and i.repimgYn = :repimgYn")
	ItemImg  findByItemIdAndRepimgYn(@Param(value = "itemId") Long itemId,
			@Param(value = "repimgYn")	String repimgYn);
	
}
