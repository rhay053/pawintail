package com.pawintail.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawintail.constant.Role;
import com.pawintail.dto.MemberFormDto;
import com.pawintail.entity.Member;
import com.pawintail.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/new")
	public String memberForm(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}
	
	@PostMapping("/new")
	public String memberForm(MemberFormDto memberFormDto) {
		Member member = Member.createMember(memberFormDto, passwordEncoder, Role.USER);
		memberService.saveMember(member);
		return "redirect:/";
	}
	@GetMapping("/login")
	public String loginMember() {
		return "member/memberLoginForm";
	}
	@GetMapping("/login/error")
	public String loginMember(Model model) {
		model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
		return "member/memberLoginForm";
	}
	
}
