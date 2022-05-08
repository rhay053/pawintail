package com.pawintail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pawintail.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	
	@Query("select m from Member m where m.email = :email")
	Member findByEmail(@Param(value="email")String email);
	
}
