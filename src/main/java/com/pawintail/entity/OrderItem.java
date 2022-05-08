package com.pawintail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_item")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity{
	@Id
	@Column(name="order_item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private int orderPrice; 
    private int count;
    
    public static OrderItem createOrderItem(Item item, int count) {
		 OrderItem orderItem = new OrderItem();
		 //주문할 상품과 주문 수량 세팅
		 orderItem.setItem(item);
		 orderItem.setCount(count);
		 //현재 시간을 기준으로 상품 가격을 주문 가격으로 세팅
		 orderItem.setOrderPrice(item.getPrice());
   	     
		 item.removeStock(count);
   	     return orderItem;    	 
    }
    
    //주문 가격 x 주문 수량
    public int getTotalPrice() {
   	   return this.orderPrice*this.count;
    }
    
    //주문 취소 시 주문 수량만큼 상품의 재고를 증가
    public void cancel() {
    	this.getItem().addStock(count);
    }
}




