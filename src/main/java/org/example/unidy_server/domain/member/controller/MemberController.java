package org.example.unidy_server.domain.member.controller;


import lombok.RequiredArgsConstructor;
import org.example.unidy_server.domain.member.dto.MemberRequestDTO;
import org.example.unidy_server.domain.member.model.service.MemberService;
import org.example.unidy_server.global.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/register")
    public BaseResponse<?> postRegisterMember(@RequestBody MemberRequestDTO dto) {
        return memberService.postRegister(dto);
    }

    // 로그인
    @PostMapping("/login")
    public BaseResponse<?> postLoginMember(@RequestBody MemberRequestDTO dto) {
        return memberService.postLogin(dto);
    }

}
