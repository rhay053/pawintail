package com.pawintail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pawintail.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query("select c from Cart c where c.member.id = :memberId")
	Cart  findByMemberId(@Param("memberId") Long memberId);
}
