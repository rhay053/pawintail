package com.pawintail.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pawintail.constant.ItemSellStatus;
//import com.pawintail.dto.ItemAllDto;
import com.pawintail.dto.ItemFormDto;
import com.pawintail.dto.ItemSearchDto;
import com.pawintail.dto.MainItemDto;
import com.pawintail.entity.Item;
import com.pawintail.service.ItemService;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@Builder
public class ItemController {

	private final ItemService itemService;
	
	
	//아이템 전체 보기
	 @GetMapping(value = "/item/itemList")
    public String mainItem(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 9);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);
        
        System.out.println("MainItem...........");

        return "item/itemList";
    }
	 
	//아이템상세페이지 호출
	@GetMapping("/item/{itemId}") 
	public String itemDetail( Model model, @PathVariable("itemId") Long itemId) {
		
			ItemFormDto itemFormDto = itemService.getItemDetail(itemId);
			model.addAttribute("item", itemFormDto);
		
			return "item/itemDetail";
		
	}
	
	//아이템 수정화면 호출
	@GetMapping("/admin/item/{itemId}") 
	public String itemDetail(@PathVariable("itemId") Long itemId, Model model) {
		try {
			ItemFormDto itemFormDto = itemService.getItemDetail(itemId);
			model.addAttribute("itemFormDto", itemFormDto);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
			model.addAttribute("itemFormDto", new ItemFormDto());
			return "item/itemForm";
		}
		return "item/itemForm";
	}
		
	
	//관리자아이템신규등록페이지 호출
	@GetMapping("/admin/item/new")
	public String itemForm(Model model) {
		model.addAttribute("itemFormDto", new ItemFormDto() );
		return "/item/itemForm";
	}
	
	//관리자아이템등록 Dto 서버로 전달
	@PostMapping("/admin/item/new")
	public String itemNew(@Valid  ItemFormDto itemFormDto, 
			BindingResult bindingResult, Model model,
			@RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) throws Exception
	{
		
		if(bindingResult.hasErrors()) {
			return "item/itemForm";
		}
		if(itemImgFileList.get(0).isEmpty() && 
				itemFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
			return "item/itemForm";	
		}
		try {
			itemService.saveItem(itemFormDto, itemImgFileList);
		} catch (IOException e) {
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
			return "item/itemForm";
		}		
		return "item/itemList";		
	}
	
	
	
	//아이템관리 페이지 get
	@GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "item/itemManage";
    }
	
	/*
	@GetMapping("admin/item/manage")
	public String itemManage(Model model) {
		
		return "item/itemMng";
	}*/
	
	//아이템 수정페이지 내용 Dto 서버로 제출
	@PostMapping("/admin/item/{itemId}") 
	public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, Model model,
			@RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
	
		if(bindingResult.hasErrors()) {
			return "item/itemForm";
		}
		
		if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수입니다.");
			return "item/itemForm";
		}

			try {
				itemService.updateItem(itemFormDto, itemImgFileList);
			} catch (Exception e) {
				model.addAttribute("errorMessage", "상품수정 중 에러가 발생했습니다.");
				return "item/itemForm";
			}

		return "redirect:/";
		
	}
	
	
	
	
	
	 
	
	
}
