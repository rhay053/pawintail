package com.pawintail.service;

import org.springframework.stereotype.Service;

import com.pawintail.dto.NoticeDto;
import com.pawintail.entity.Notice;
import com.pawintail.repository.NoticeRepository;

@Service
public class NoticeService {

	private NoticeRepository noticeRepository;
	/*
	public Long saveNotice(NoticeDto noticeDto) {
		
		Notice notice = noticeDto.createNotice();
		noticeRepository.save(notice);
		
		return noticeRepository.get;
		
		
	}
	*/
}
