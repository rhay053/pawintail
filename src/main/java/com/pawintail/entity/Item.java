package com.pawintail.entity;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pawintail.Exception.OutOfStockException;
import com.pawintail.constant.ItemSellStatus;
import com.pawintail.dto.ItemFormDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="item_seq_gen", sequenceName = "item_seq", initialValue = 1, allocationSize = 1)
public class Item extends BaseEntity{

	@Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;       //상품 코드
	
	@Column(nullable = false, length = 50)
	private String itemName; //상품명
	
	@Column(nullable = false, length = 50)
	private Integer itemWeight; //상품무게
	
	@Column(name="price", nullable = false) 
	private Integer price; //가격
	
	@Column(nullable = false)
	private Integer stockNumber; //재고수량
	
	@Column(name="pet_type")
	private String petType; //개-고양이
	
	@Column(name="food_type")
	private String foodType; //사료-간식
	
    @Column(nullable = false)
	private String itemDetail; //상품설명
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus; //상품 판매 상태enum
	
	private String dawnDelYn; //새벽배송여부 "Y/N"

	public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }
	
	//상품 주문 상품의 재고를 감소시키는 로직
	public void removeStock(int stockNumber) {
		int restStock = this.stockNumber - stockNumber;
		if(restStock<0){
			throw new OutOfStockException("상품의 재고가 부족합니다.(현재 재고 수량: " + this.stockNumber + ")");
			}
		this.stockNumber = restStock;
		}
	
	//상품 재고 증가 메소드
	public void addStock(int stockNumber) {
		this.stockNumber += stockNumber;
	}
}
