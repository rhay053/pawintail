package com.pawintail.repository;

import com.pawintail.entity.Mypet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MypetRepository extends JpaRepository<Mypet, Integer> {

    Page<Mypet> findByTitleContaining(String searchKeyword, Pageable pageable);
}