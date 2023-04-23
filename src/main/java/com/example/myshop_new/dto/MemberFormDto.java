package com.example.myshop_new.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class MemberFormDto {

     private String name;

     private String email;

     private String password;

     private String address;

}
