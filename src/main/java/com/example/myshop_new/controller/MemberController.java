package com.example.myshop_new.controller;

import com.example.myshop_new.dto.MemberFormDto;
import com.example.myshop_new.entity.Member;
import com.example.myshop_new.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String getMemberForm(Model model){
        MemberFormDto memberFormDto = new MemberFormDto();
        model.addAttribute("memberFormDto", memberFormDto);
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String postMemberForm(MemberFormDto memberFormDto) {
        memberService.saveMember(memberFormDto);
        // 메인 페이지로 리다이렉션
        return "redirect:/";
    }

}
