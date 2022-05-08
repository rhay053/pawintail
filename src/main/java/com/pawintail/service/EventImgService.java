package com.pawintail.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.pawintail.entity.EventImg;
import com.pawintail.repository.EventImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EventImgService {

	
	@Value("${eventImgLocation}") //properties에 이미지 저장할 경로 지정
    private String eventImgLocation;
	
	private final EventImgRepository eventImgRepository;

    private final FileService fileService;
    
    //EvnetImg 등록
    public void saveEventImg(EventImg eventImg, MultipartFile eventImgFile) throws Exception{
        String oriImgName = eventImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(eventImgLocation, oriImgName,
                    eventImgFile.getBytes());
            imgUrl = "/images/event/" + imgName;
        }

        //이벤트 이미지 정보 저장
        eventImg.updateEventImg(oriImgName, imgName, imgUrl);
        eventImgRepository.save(eventImg);
    }
    
    public void updateEventImg(Long eventImgId, MultipartFile eventImgFile) throws Exception{
        if(!eventImgFile.isEmpty()){
            EventImg savedEventImg = eventImgRepository.findById(eventImgId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedEventImg.getEventImgName())) {
                fileService.deleteFile(eventImgLocation+"/"+
                		savedEventImg.getEventImgName());
            }

            String oriImgName = eventImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(eventImgLocation, oriImgName, eventImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedEventImg.updateEventImg(oriImgName, imgName, imgUrl);
        }
    }
}
