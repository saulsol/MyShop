package com.example.myshop_new.service;

import com.example.myshop_new.dto.MemberFormDto;
import com.example.myshop_new.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        MemberFormDto memberFormDto = MemberFormDto.builder()
                .name("임솔")
                .email("dlathf3210@naver.com")
                .address("홍천")
                .password("dlathf3210")
                .build();

        memberService.saveMember(memberFormDto);
    }

    @Test
    @DisplayName("중복 회원가입 테스트")
    public void saveDuplicateTest(){

        MemberFormDto memberFormDto = MemberFormDto.builder()
                .name("임솔")
                .email("dlathf3210@naver.com")
                .address("홍천")
                .password("dlathf3210")
                .build();

        MemberFormDto memberFormDto2 = MemberFormDto.builder()
                .name("임솔")
                .email("dlathf3210@naver.com")
                .address("홍천")
                .password("dlathf3210")
                .build();

        memberService.saveMember(memberFormDto);

        Throwable e = assertThrows(IllegalStateException.class, ()-> {
            memberService.saveMember(memberFormDto2);
        });

        assertEquals("이미 가입된 회원입니다.", e.getMessage());

    }

}