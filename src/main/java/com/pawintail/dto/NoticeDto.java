package com.pawintail.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.pawintail.entity.Notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {

	private Long id;
	@NotBlank(message = "제목은 필수 입력 값입니다.")
	private String title;
	@NotBlank(message = "내용은 필수 입력 값입니다.")
	private String content;
	
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private int replyCount;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Notice createNotice() {
		return modelMapper.map(this, Notice.class);
	}
	
	public static NoticeDto of(Notice notice) {
		return modelMapper.map(notice, NoticeDto.class);
	}
}
