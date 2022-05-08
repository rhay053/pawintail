package com.pawintail.dto;

import java.time.LocalDate;

import com.pawintail.constant.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileDto {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String memberAddress;

    private Role role;

    private String phoneNumber;

    private String birthday;

    // private int visitCount;

    // private int orderCount;

    // private LocalDate createdAt;
    
}
