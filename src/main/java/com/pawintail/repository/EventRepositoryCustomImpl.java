package com.pawintail.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.pawintail.dto.EventMainDto;
import com.pawintail.dto.EventSearchDto;
import com.pawintail.dto.QEventMainDto;
import com.pawintail.entity.Event;
import com.pawintail.entity.QEvent;
import com.pawintail.entity.QEventImg;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class EventRepositoryCustomImpl implements EventRepositoryCustom{
	
	private JPAQueryFactory queryFactory;
	
	public EventRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	//EventMainDto에 쿼리조건에 맞는 데이터담도록 작성 -> html에서 출력, controller에서 라우터지정
	
	@Override
	public Page<EventMainDto> getEventMainPage(EventSearchDto eventSearchDto, Pageable pageable) {
		QEvent event = QEvent.event;
		QEventImg eventImg = QEventImg.eventImg;
		
		List<EventMainDto> results = queryFactory
				.select(new QEventMainDto(event.eventId, event.eventName, eventImg.eventImgUrl))
				.from(eventImg)
				.join(eventImg.event, event)
				.where(eventImg.eventRepImgYn.eq("Y"))
				.where(eventNameLike(eventSearchDto.getSearchQuery()))
				.orderBy(event.eventId.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		return new PageImpl<EventMainDto>(results, pageable, results.size());
	}
	
	private BooleanExpression eventNameLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery) ? null : QEvent.event.eventName.like("%" + searchQuery + "%");
    }

	//이벤트관리 페이지 데이터 가져와서 페이징
	@Override
	public Page<Event> getAdminEventMainPage(EventSearchDto eventSearchDto, Pageable pageable) {
		List<Event> results = queryFactory
				.selectFrom(QEvent.event)
				.where(regDtsAfter(eventSearchDto.getSearchDateType()), 
						searchByLike(eventSearchDto.getSearchBy(), eventSearchDto.getSearchQuery())
						)
				.orderBy(QEvent.event.eventId.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		return new PageImpl<Event>(results, pageable, results.size());
	}

	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("eventName", searchBy)) {
			return QEvent.event.eventName.like("%"+searchBy+"%");
		} else if(StringUtils.equals("createdBy", searchBy)) {
			return QEvent.event.createdBy.like("%"+searchBy+"%");
		}
		return null;
	}
	
	private BooleanExpression regDtsAfter(String searchDateType) {
		LocalDateTime dateTime = LocalDateTime.now();
		if(StringUtils.equals("all", searchDateType) || searchDateType == null) {
			return null;
		} else if(StringUtils.equals("1일", searchDateType)) {
			dateTime = dateTime.minusDays(1);
		} else if(StringUtils.equals("1주", searchDateType)) {
			dateTime = dateTime.minusWeeks(1);
		} else if(StringUtils.equals("1개월", searchDateType)) {
			dateTime = dateTime.minusMonths(1);
		} else if(StringUtils.equals("6개월", searchDateType)) {
			dateTime = dateTime.minusMonths(6);
		} 
		return QEvent.event.regTime.after(dateTime);
	}
	
	
}
