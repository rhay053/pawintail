package com.pawintail.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pawintail.constant.OrderStatus;
import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
	 @Id 	
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "order_id")
	 private Long id;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "member_id")
     private Member member;
	 
     private LocalDateTime orderDate; //주문일

	 
     @Enumerated(EnumType.STRING)
     private OrderStatus orderStatus; //주문상태     
     @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
     private List<OrderItem> orderItems = new ArrayList<>();

     public void addOrderItem(OrderItem orderItem) {
    	 orderItems.add(orderItem);
    	 orderItem.setOrder(this);
     }
     public static Order createOrder(Member member, 
    		 List<OrderItem> orderItemList) {
    	 Order order = new Order();
    	 order.setMember(member);
    	 for(OrderItem orderItem: orderItemList) {
    		 order.addOrderItem(orderItem);
    	 }
    	 order.setOrderStatus(OrderStatus.ORDER);
    	 order.setOrderDate(LocalDateTime.now());
    	 return order;
     }
     public int getTotalPrice() {
    	 int totalPrice = 0;
    	 for(OrderItem orderItem : orderItems) {
    		 totalPrice += orderItem.getTotalPrice();
    	 }
    	 return totalPrice;
     }
     public void cancelOrder() {
    	 this.orderStatus = orderStatus.CANCEL;
    	 for(OrderItem orderItem : orderItems) {
    		 orderItem.cancel();
    	 }
     }
}



