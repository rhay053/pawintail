package com.pawintail.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Notice extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "notice_seq_gen")
	private Long nno;
	
	private String title;

	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Member writer;
	
	public void changeTitle(String title) {
		this.title = title;
	}
	public void changeContent(String content) {
		this.content = content;
	}
	
	
}
