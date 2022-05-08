package com.pawintail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "mypet")
@ToString(exclude = {"member"})
public class Mypet {

	//null값을 삽입하지 않으려면 오라클에서 PK(sequence) 생성되었는지 꼭 확인
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column    
    private String title;
    @Column
    private String content;
    @Column
    private String filename;
    @Column
    private String filepath;
    
    //작성자 임의 추가
    @Column 
    private String writer;
    
//작성자 추가 위한 매핑
//    @ManyToOne(fetch=FetchType.LAZY)
//	private Member member;

}