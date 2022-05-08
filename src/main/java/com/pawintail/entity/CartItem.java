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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cart_item")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem extends BaseEntity{
	@Id
	@Column(name="cart_item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;
    private int count;
    
    public static CartItem createCartItem(Cart cart, Item item, int count) {
    	CartItem cartItem = new CartItem();
    	cartItem.setCart(cart);
    	cartItem.setItem(item);
    	cartItem.setCount(count);
    	return cartItem;
    }
    public void addCount(int count) {
    	this.count += count;
    }
    public void updateCount(int count) {
    	this.count = count;
    }
}







