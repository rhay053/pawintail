package com.pawintail.entity;


import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass //created-modified 를 자동으로 칼럼으로 인식
@EntityListeners(value = {AuditingEntityListener.class}) //시간자동지정하는 BaseTimeEntity 상속받음
@Getter
public abstract class BaseEntity extends BaseTimeEntity {
	
	@CreatedBy
    @Column(updatable = false)
	private String createdBy;
    @LastModifiedBy
    private String modifiedBy;
}
