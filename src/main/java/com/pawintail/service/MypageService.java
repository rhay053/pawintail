package com.pawintail.service;

import java.util.List;

import com.pawintail.dto.ProfileDto;
import com.pawintail.entity.Member;


public interface MypageService {
    public void register(Member member) throws Exception;

    public Member read(Long id) throws Exception;

    public void modify(Member member) throws Exception;

    public List<Member> list() throws Exception;

    public ProfileDto showProfileData(String email) throws Exception;

    void updateProfile(String email, ProfileDto profileDto);

    Long changePassword(Long id, String password);
    //비번 변경
    void deleteMemberByEmail(String email);

    
}
