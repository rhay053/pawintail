package com.pawintail.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.pawintail.dto.ProfileDto;
import com.pawintail.entity.Member;
import com.pawintail.service.MypageService;
import com.pawintail.service.MypageServiceImpl;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Log4j2
public class MypageController {
    private final MypageService mypageServiceImpl;   
    
    @GetMapping
    private String MypageDto(){
    	log.info("*******************profile*******************");
        return "mypage/mypage";
    }
    
    @GetMapping("/profile")    
    public String editDataPage(Principal principal, Model model) throws Exception {
    	
        try {
			log.info("*******************profile*******************");
			System.out.println("*******************profile*******************");
		} catch (Exception e) {
			log.info(e +"===========================");
		}
      //  log.info(principal.getName());
       String email = principal.getName();
      //  log.info("**************************************:email"+email);
        
        ProfileDto myProfileDto = mypageServiceImpl.showProfileData(email);
        log.info("****:myProfileDto" + myProfileDto);
        model.addAttribute("member", myProfileDto);

        return "mypage/profile";
    }

    @GetMapping("/update")
    public String userUpdate() {
        return "member/update";
    }
    
}
