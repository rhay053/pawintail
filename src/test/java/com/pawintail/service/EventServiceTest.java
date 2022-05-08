package com.pawintail.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pawintail.dto.EventFormDto;
import com.pawintail.entity.Event;
import com.pawintail.repository.EventImgRepository;
import com.pawintail.repository.EventRepository;


@SpringBootTest
@Transactional

public class EventServiceTest {

	@Autowired
	EventService eventService;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	EventImgRepository eventImgRepository;
	
	//이벤트이미지가 5개등록
	
	List<MultipartFile> createMultipartFile() {
		List<MultipartFile> multipartFileList = new ArrayList<MultipartFile>();
		for(int i = 0; i < 5; i++) {
			String path ="c:/KOSTA/pawintail/event";
			String imageName = "image"+i+".jpg";
			MockMultipartFile multipartFile =
					new MockMultipartFile(path, imageName, 
							"image/jpg",new byte[] {1,2,3,4});
			multipartFileList.add(multipartFile);
			System.out.println(multipartFileList);
		}
		return multipartFileList;
	}
	
	/*
	@Test
	@DisplayName("이벤트 등록 테스트")
	@WithMockUser(username = "admin", roles="ADMIN")
	@Commit
	void saveEvent() throws Exception {
		EventFormDto eventFormDto =new EventFormDto();
		eventFormDto.setEventName("테스트 이벤트3");

		List<MultipartFile> multipartFileList = this.createMultipartFile();
		
		Long eventId = eventService.saveEvent(eventFormDto, multipartFileList);
		
		Event event = eventRepository.findByEventId(eventId);
		
		System.out.println(eventFormDto.getEventName());
	}
	*/
	
	//jpql 쿼리) 부모자식레코드 각각 삭제 작성
	/* 
	@Test
	@DisplayName("이벤트 삭제 테스트")
	@WithMockUser(username = "admin", roles="ADMIN")
	@Commit
	void removeEvent() throws Exception {
		
		Long eventId = 26L;
		
		System.out.println("삭제 eventId: "+eventId);
		eventService.removeWithEventImgs(eventId);
	}
	*/
	
	//cascade) Event, EventImg 엔티티에 @OneToMany, @OnDelete추가 부모레코드 삭제시 자식레코드도 같이 삭제
	/* 
	@Test
	@DisplayName("이벤트 삭제 테스트")
	@WithMockUser(username = "admin", roles="ADMIN")
	@Commit
	void removeEvent() throws Exception {
		
		Long eventId = 1L;
		
		System.out.println("삭제 eventId: "+eventId);
		eventRepository.deleteById(eventId);
	}
	*/
}
