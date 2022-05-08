package com.pawintail.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.pawintail.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> ,ItemRepositoryCustom, QuerydslPredicateExecutor<Item> 
{
	
	@Query("select i from Item i where i.itemName = :itemName or i.itemDetail = :itemDetail ")
    List<Item> findByItemNameOrItemDetail(@Param("itemName")String itemName,@Param("itemDetail") String itemDetail);	
	
	@Query("select i from Item i where  i.price < :price")
    List<Item> findByPriceLessThan(Integer price);
	
	@Query("select i from Item i where  i.price < :price order by i.price desc")
    List<Item> findByPriceLessThanOrderByPriceDesc(@Param("price") Integer price);

    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
	
	
	

}
