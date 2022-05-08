package com.pawintail.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.pawintail.constant.OrderStatus;
import com.pawintail.entity.Order;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Getter @Setter
@Log4j2
public class OrderHistDto {

	public OrderHistDto(Order order ) {
		  this.orderId = order.getId();
		 
		  log.info("주문번호 :  ===== " + orderId);
		  
	      //this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	      this.orderDate = order.getOrderDate().toString();
	      this.orderStatus = order.getOrderStatus();
	}
	private Long orderId; //주문아이디
    private String orderDate; //주문날짜
    private OrderStatus orderStatus; //주문 상태

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    //주문 상품리스트
    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }

}
