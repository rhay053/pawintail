package com.pawintail.service;

import com.pawintail.entity.Member;
import com.pawintail.entity.Mypet;
import com.pawintail.repository.MypetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class MypetService {

    @Autowired
    private MypetRepository mypetRepository;

    // 글 작성 처리
    public void write(Mypet mypet, MultipartFile file) throws Exception{

//        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\mypet";

        String projectPath = "c:\\KOSTA\\OurWorkspace\\pawintail\\src\\main\\resources\\static\\files\\mypet";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        mypet.setFilename(fileName);
        mypet.setFilepath("/files/mypet/" + fileName);

        mypetRepository.save(mypet);
    }

    // 게시글 리스트 처리
    public Page<Mypet> mypetList(Pageable pageable) {

        return mypetRepository.findAll(pageable);
    }

    public Page<Mypet> mypetSearchList(String searchKeyword, Pageable pageable) {

        return mypetRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 특정 게시글 불러오기
    public Mypet mypetView(Integer id) {

        return mypetRepository.findById(id).get();
    }
    
    //삭제처리
	public Mypet delete(Integer id) {
		//대상 찾기
		Mypet target = mypetRepository.findById(id).orElse(null);
		//잘못된 요청처리
		if (target == null) {
			return null;	
			}
		//대상 삭제
		mypetRepository.delete(target);
		return target;
	}}
