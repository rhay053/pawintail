package com.pawintail.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.pawintail.constant.ItemSellStatus;
import com.pawintail.dto.OrderDto;
import com.pawintail.entity.Item;
import com.pawintail.entity.Member;
import com.pawintail.entity.Order;
import com.pawintail.entity.OrderItem;
import com.pawintail.repository.ItemRepository;
import com.pawintail.repository.MemberRepository;
import com.pawintail.repository.OrderRepository;

@SpringBootTest
@Transactional
class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	public Item saveItem() {
		Item item = new Item();
		item.setItemName("테스트상품");
		item.setPrice(10000);
		item.setItemDetail("테스트 상품 상세 설명");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		return itemRepository.save(item);
	}
	
	public Member saveMember() {
		Member member = new Member();
		member.setEmail("user@test.com");
		return memberRepository.save(member);
	}
	
//	@Test
//	@DisplayName("주문 테스트")
//	public void order() {
//		Item item = saveItem();
//		Member member = saveMember();
//		
//		OrderDto orderDto = new OrderDto();
//		orderDto.setCount(10);
//		orderDto.setItemId(item.getId());
//		
//		Long orderId = orderService.order(orderDto, member.getEmail());
//				
//		Order order = orderRepository.findById(orderId)
//			.orElseThrow(EntityNotFoundException::new);
//		
//		List<OrderItem> orderItems = order.getOrderItems();
//		
//		int totalPrice =orderDto.getCount()*item.getPrice();
//		
//		assertEquals(totalPrice, order.getTotalPrice());
//	
//	
//	}
}
