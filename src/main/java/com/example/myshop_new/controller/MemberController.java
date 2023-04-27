package com.example.myshop_new.controller;

import com.example.myshop_new.dto.MemberFormDto;
import com.example.myshop_new.entity.Member;
import com.example.myshop_new.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


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
    public String postMemberForm(@Valid MemberFormDto memberFormDto,
                                 BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }

        try {
            memberService.saveMember(memberFormDto);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping("/login/error") // Security Config 에서 로그인 실패시 해당 URL 로 이동시킴
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }


}
