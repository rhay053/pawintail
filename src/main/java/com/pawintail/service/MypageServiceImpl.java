package com.pawintail.service;

import java.util.List;
import java.util.Optional;


import com.pawintail.dto.ProfileDto;
import com.pawintail.entity.Member;
import com.pawintail.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {
    private  final   MemberRepository memberRepository;


    @Override
    @Transactional (readOnly = true)
    public Member read(Long id) throws Exception {
        return this.memberRepository.getById(id);
    }

    @Override
    @Transactional
    public void modify(Member member) throws Exception {
        Member memberEntity = this.memberRepository.getById(member.getId());
        member.setEmail(member.getEmail());
        member.setName(member.getName());
        member.setMemberAddress(member.getMemberAddress());
        member.setBirthday(member.getBirthday());
        member.setPhoneNumber(member.getPhoneNumber());
        
    }


    @Override
    public void register(Member member) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> list() throws Exception {
   
        return memberRepository.findAll(Sort.by(Direction.DESC, "id"));
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDto showProfileData(String email) throws Exception {
        ProfileDto myProfileDto = new ProfileDto();

        Member findMember = memberRepository.findByEmail(email);

        myProfileDto.setName(findMember.getName());
        myProfileDto.setMemberAddress(findMember.getMemberAddress());
        myProfileDto.setPhoneNumber(findMember.getPhoneNumber());
        myProfileDto.setEmail(findMember.getEmail());
        myProfileDto.setBirthday(findMember.getBirthday());
        return myProfileDto;

    }
    
    @Transactional
    @Override
    public void updateProfile(String email, ProfileDto profileDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

       // Member findMember = memberRepository.findById(id).orElseThrow(
         //       () -> new LoginIdNotFoundException("해당하는 회원을 찾을 수 없습니다"));

        Member findMember = memberRepository.findByEmail(email);
        findMember.setName(profileDto.getName());
        findMember.setEmail(profileDto.getEmail());
        findMember.setPassword(passwordEncoder.encode(profileDto.getPassword()));

        findMember.setPhoneNumber(profileDto.getPhoneNumber());
        findMember.setEmail(profileDto.getEmail());
        findMember.setMemberAddress(profileDto.getMemberAddress());
    }
    

    @Override
    public Long changePassword(Long id, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteMemberByEmail(String email) {
        // TODO Auto-generated method stub
        
    }

    
}
