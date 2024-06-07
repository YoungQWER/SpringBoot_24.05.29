package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/new")
    public String newMember(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());

        return "member/memberForm";
    }


}
