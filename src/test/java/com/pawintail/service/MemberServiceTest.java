package com.pawintail.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import com.pawintail.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceTest {
	/*
	@Autowired
	MemberRepository memberRepository;
	
	//테이블접근해서하는 작업(commit..)시 매니저이용
	@PersistenceContext
	EntityManager em;*/
	/*
	//임시유저가 등록이 되는지 확인만 해봄
	@Test
	@DisplayName("Auditing 테스트")
	@WithMockUser(username = "gildong", roles="USER")
	public void auditingTest() {
		Member newMember = new Member();
		memberRepository.save(newMember);
		
		em.flush(); //데이터베이스 실행시킴
		em.clear(); //데이터베이스 내용 날림
		//em.getTransaction().commit(); //가져와서 데이터 저장까지함
		Member member = memberRepository.findById(newMember.getId())
				.orElseThrow(EntityNotFoundException::new);
		System.out.println(member.getCreatedBy());
		System.out.println(member.getModifiedBy());
		System.out.println(member.getRegTime());
		System.out.println(member.getUpdateTime());
	}
	*/
}
