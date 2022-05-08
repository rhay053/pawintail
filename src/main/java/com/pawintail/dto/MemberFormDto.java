package com.pawintail.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.pawintail.constant.Role;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberFormDto {
	
	 private Long id;
		
		@NotBlank(message = "이름은 필수 입력 값입니다.")
	    private String name;
		
	    @Column(unique = true)
	    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
		@Email(message="이메일 형식으로 입력해주세요")
	    private String email;
	    
	    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
		@Length(min = 8, max = 20, message = "비밀번호는 8자이상, 20자 이하로 입력하세요")
	    private String password;
	    
	    @NotEmpty(message = "주소는 필수 입력 값입니다.")
	    private String memberAddress;

	    private Role role;

	    private String phoneNumber;

	    private String birthday;

	    // private int visitCount;

	    // private int orderCount;

	    // private LocalDate createdAt;


	    @QueryProjection
	    public MemberFormDto(Long id, String name, 
	            String memberAddress, String email, Role role, String phoneNumber, int visitCount, int orderCount, LocalDate createdAt) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.role = role;
	        this.phoneNumber = phoneNumber;
			this.birthday = birthday;
	        this.memberAddress = memberAddress;
	        // this.visitCount = visitCount;
	        // this.orderCount = orderCount;
	        // this.createdAt = createdAt;
	    }
	}// Querydsl을 위한 Dto
    
    

