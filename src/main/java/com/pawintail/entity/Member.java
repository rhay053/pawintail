package com.pawintail.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.pawintail.constant.Role;
import com.pawintail.dto.MemberFormDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {
	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String memberAddress;
	private String birthday;
	private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder, Role role) {
		String password = passwordEncoder.encode(memberFormDto.getPassword());//화면에 입력받은pw를 enPw로 엔코딩

		Member member = Member.builder()
				.name(memberFormDto.getName())
				.email(memberFormDto.getEmail())
				.memberAddress(memberFormDto.getMemberAddress())
				.birthday(memberFormDto.getBirthday())
				.phoneNumber(memberFormDto.getBirthday())
				.password(password)
				.role(Role.USER).build();

    	return member;
    	
    }

}

