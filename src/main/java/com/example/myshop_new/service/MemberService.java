package com.example.myshop_new.service;

import com.example.myshop_new.dto.MemberFormDto;
import com.example.myshop_new.entity.Member;
import com.example.myshop_new.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public void saveMember(MemberFormDto memberFormDto){
        validateDuplicateMember(memberFormDto.getEmail()); // 검증
        Member newMember = Member.createMember(memberFormDto, passwordEncoder);
        memberRepository.save(newMember);
    }

    private void validateDuplicateMember(String memberEmail){

         Optional<Member> optionalMember = memberRepository.findByEmail(memberEmail); // NPE 방지
         if(optionalMember.isPresent()){
             log.info("{} 이미 가입된 이메일", memberEmail);
             throw new IllegalStateException("이미 가입된 회원입니다.");
         }else{
             log.info("{} 새로운 이메일", memberEmail);
         }

    }

}
