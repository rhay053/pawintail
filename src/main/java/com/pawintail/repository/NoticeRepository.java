package com.pawintail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pawintail.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

	// @Query("select n, w from Notice n left join b.writer w where b.bno = :bno")
	//    Object getBoardWithWriter(@Param("bno") Long bno);
}
