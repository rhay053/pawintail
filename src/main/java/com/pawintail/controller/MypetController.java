package com.pawintail.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pawintail.entity.Member;
import com.pawintail.entity.Mypet;
import com.pawintail.repository.MypetRepository;
import com.pawintail.service.MypetService;

@Controller
@RequestMapping("/mypet/")
public class MypetController {


    @Autowired
    private MypetService mypetService;
    
    @Autowired
	private MypetRepository mypetRepository;
    
    
    //글작성폼(model 작성 후 객체 저장 필요) > write.html에서 <input type="hidden" th:field="*{id}"> 추가
    @GetMapping("/write")
    public String mypetWriteForm(Model model) {
    	model.addAttribute("mypet", new Mypet());
        return "mypet/write";
    }

    //글작성 처리
    @PostMapping("/writepro")
    public String mypetWritePro(Mypet mypet, Model model, MultipartFile file) throws Exception{

        mypetService.write(mypet, file);

        model.addAttribute("searchUrl", "/mypet/list");

        return "mypet/view";
    }
    
    //리스트 처리
    @GetMapping("/list")
    public String mypetList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Mypet> list = null;

        if(searchKeyword == null) {
            list = mypetService.mypetList(pageable);
        }else {
            list = mypetService.mypetSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "mypet/list";
    }
    //상세페이지 처리
    @GetMapping("/view")
    public String mypetView(Model model, Integer id) {

        model.addAttribute("mypet", mypetService.mypetView(id));
        return "mypet/view";
    }
    
    //수정폼 작성
    @GetMapping("/modify/{id}")
    public String mypetModify(@PathVariable(value="id", required = false) Integer id,
                              Model model) {

        model.addAttribute("mypet", mypetService.mypetView(id));

        return "mypet/modify";
    }
    
    //수정폼 처리
    @PostMapping("/update/{id}")
    public String mypetUpdate(@PathVariable(value="id", required = false) Integer id, Mypet mypet, MultipartFile file) throws Exception{

    	Mypet mypetTemp = mypetService.mypetView(id);
        
    	mypetTemp.setTitle(mypet.getTitle());
    	mypetTemp.setContent(mypet.getContent());
    	//File 수정 추가
    	mypetTemp.setFilename(mypet.getFilename());
    	mypetTemp.setFilepath(mypet.getFilepath());

    	mypetService.write(mypetTemp, file);

        return "redirect:/mypet/list";

    }
    
    //작성글 삭제
    @GetMapping("/delete/{id}")
	public String delete(@PathVariable(value="id", required = false) Integer id, RedirectAttributes rttr) {
		Mypet target = mypetRepository.findById(id).orElse(null);
		if (target != null) {
			mypetRepository.delete(target);
			rttr.addFlashAttribute("msg", "삭제가 완료 되었습니다!");
		}
		return "redirect:/mypet/list";
	}
}