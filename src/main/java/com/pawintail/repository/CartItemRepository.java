package com.pawintail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pawintail.dto.CartDetailDto;
import com.pawintail.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	@Query("select i from CartItem i where i.cart.id = :cartId and i.item.id = :itemId  ")
	CartItem findByCartIdAndItemId(@Param("cartId") Long cartId,
			@Param("itemId") Long itemId);
	
	//Long cartItemId, String itemName, int price, int count, String imgUrl
	
	//@Query("select new CartDetailDto( ci.id, i.itemName, i.price, ci.count, im.imgUrl) "
	@Query("select ci.id, i.itemName, i.price, ci.count, im.imgUrl "
			+ "from CartItem ci "
			+ "left join ci.item i "
			+ "left join ItemImg im "
			+ "on ci.item.id = im.item.id "
			+ "where im.repimgYn='Y' and "
			+ "ci.cart.id = :cartId "
			+ "order by ci.regTime  ")
	List<Object[]> findCartDetailDtoList(@Param("cartId") Long cartId);
}









