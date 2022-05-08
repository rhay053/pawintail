package com.pawintail.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.pawintail.constant.ItemSellStatus;
import com.pawintail.dto.ItemSearchDto;
import com.pawintail.dto.MainItemDto;
import com.pawintail.dto.QMainItemDto;
import com.pawintail.entity.Item;
import com.pawintail.entity.QItem;
import com.pawintail.entity.QItemImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
	
	private JPAQueryFactory queryFactory;

	public ItemRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
		return searchSellStatus == null? 
				null : QItem.item.itemSellStatus.eq(searchSellStatus);
	}
	
	private BooleanExpression itemNameLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemName.like("%" + searchQuery + "%");
    }
	
	@Override
	public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
		//전체페이지추가
		QItem item = QItem.item;
		QItemImg itemImg = QItemImg.itemImg;
		List<Long> total = queryFactory
				.select(item.id.count())
				.from(itemImg)
				.join(itemImg.item,item)
				.where(itemImg.repimgYn.eq("Y"))
				.where(itemNameLike(itemSearchDto.getSearchQuery()))
				.fetch();
		
		List<MainItemDto> content = queryFactory
				.select(new QMainItemDto
						(item.id, item.itemName, item.itemDetail,itemImg.imgUrl, item.price))
				.from(itemImg)
				.join(itemImg.item,item)
				.where(itemImg.repimgYn.eq("Y"))
				.where(itemNameLike(itemSearchDto.getSearchQuery()))
				.orderBy(item.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		return new PageImpl<MainItemDto>(content, pageable, total.get(0));
		
		/*
		QItem item = QItem.item;
		QItemImg itemImg = QItemImg.itemImg;
		List<MainItemDto> results = queryFactory
				.select(new QMainItemDto
						(item.id, item.itemName, item.itemDetail,itemImg.imgUrl, item.price))//item.itemSellStatus 추가?
				.from(itemImg)
				.join(itemImg.item,item)
				.where(itemImg.repimgYn.eq("Y"))
				.where(itemNameLike(itemSearchDto.getSearchQuery()))
				.orderBy(item.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		return new PageImpl<MainItemDto>(results, pageable, results.size());
		*/
	}

	@Override
	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
		//전체페이지 추가
		QItem qItem  =QItem.item;
		List<Long> total = queryFactory
				.select(qItem.id.count())
				.from(qItem)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(),
                                itemSearchDto.getSearchQuery()))
                .orderBy(QItem.item.id.desc())
                .fetch();
		List<Item> content = queryFactory
	                .selectFrom(QItem.item)
	                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
	                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
	                        searchByLike(itemSearchDto.getSearchBy(),
	                                itemSearchDto.getSearchQuery()))
	                .orderBy(QItem.item.id.desc())
	                .offset(pageable.getOffset())
	                //.offset(0)
	                .limit(pageable.getPageSize())
					.fetch();
		
		return new PageImpl<>(content, pageable, total.get(0));
		/*List<Item> results = queryFactory
				.selectFrom(QItem.item)
				.where(regDtsAfter(itemSearchDto.getSearchDateType()), 
						searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
						searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery())
						)
				.orderBy(QItem.item.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		return new PageImpl<Item>(results, pageable, results.size() );
		*/
	}
	
	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now();
		if(StringUtils.equals("all", searchDateType) || searchDateType == null) {
			return null;
		} else if(StringUtils.equals("1일", searchDateType)) {
			dateTime = dateTime.minusDays(1);
		} else if(StringUtils.equals("1주일", searchDateType)) {
			dateTime = dateTime.minusWeeks(1);
		} else if(StringUtils.equals("1개월", searchDateType)) {
			dateTime = dateTime.minusMonths(1);
		} else if(StringUtils.equals("6개월", searchDateType)) {
			dateTime = dateTime.minusMonths(6);
		} 
		return QItem.item.regTime.after(dateTime);
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("itemName", searchBy)) {
			return QItem.item.itemName.like("%"+searchBy+"%");
		} else if(StringUtils.equals("createdBy", searchBy)) {
			return QItem.item.createdBy.like("%"+searchBy+"%");
		}
		return null;
	}
	
	
	
	
}
