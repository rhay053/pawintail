package com.pawintail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="event_img")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventImg extends BaseEntity{//admin) event등록화면에서 받은 데이터 저장

	@Id
    @Column(name="event_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "event_img_seq_gen")
    private Long id;
	
	private String eventImgName; //이미지 파일명

    private String eventOriImgName; //원본 이미지 파일명

    private String eventImgUrl; //이미지 조회 경로

    private String eventRepImgYn; //대표 이미지 여부
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    
    public void updateEventImg(String eventOriImgName, String eventImgName, String eventImgUrl){
       this.eventOriImgName = eventOriImgName;
       this.eventImgName = eventImgName;
       this.eventImgUrl = eventImgUrl;
       
    }
}
