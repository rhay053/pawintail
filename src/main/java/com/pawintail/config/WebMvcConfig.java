package com.pawintail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{ //html에서 받은 멀티파트 파일 업로드 경로 저장
	
	@Value("${mypetImgLocation}")
	String mypetImgLocation;
	
	@Value("${itemImgLocation}")
	String itemImgLocation;

	@Value("${eventImgLocation}") //properties에 이미지 저장할 경로 지정
    private String eventImgLocation;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/images/**").addResourceLocations(uploadPath);
		registry.addResourceHandler("/images/item/**").addResourceLocations(itemImgLocation);
		registry.addResourceHandler("/images/event/**").addResourceLocations(eventImgLocation);
		//우리집멍냥이(mypet) 사진 file 업로드 경로 추가
	    registry.addResourceHandler("/files/mypet/**").addResourceLocations(mypetImgLocation);
	}
}
	//properties에서 지정한 upload경로 밑에 images폴더 아래에 파일 등록
	//itemImgLocation
  //http://localhost:9090/images/item/9d7dbce4-a5a2-4364-963b-47e5f71bfdd9.jpg
