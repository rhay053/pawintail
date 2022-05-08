package com.pawintail.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(value = {AuditingEntityListener.class}) //jpa에서 생성일,수정일등 시간값 자동으로 입력
@MappedSuperclass
@Getter @Setter
public class BaseTimeEntity {

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime regTime;
	
	@LastModifiedDate
	private LocalDateTime updateTime;
	
	@PrePersist
	public void prePersist() {
		this.regTime = LocalDateTime.now();
		this.updateTime = LocalDateTime.now();
	}
	@PreUpdate 
	void preUpdate() {		
		this.updateTime = LocalDateTime.now();
	}
}
