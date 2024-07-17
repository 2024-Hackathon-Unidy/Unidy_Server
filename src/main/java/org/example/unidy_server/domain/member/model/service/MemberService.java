package org.example.unidy_server.domain.member.model.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.unidy_server.domain.member.dto.MemberRequestDTO;
import org.example.unidy_server.domain.member.entity.MemberEntity;
import org.example.unidy_server.domain.member.model.enums.LanguageType;
import org.example.unidy_server.domain.member.repository.MemberRepository;
import org.example.unidy_server.global.auth.JwtUtils;
import org.example.unidy_server.global.exception.CustomErrorCode;
import org.example.unidy_server.global.exception.CustomException;
import org.example.unidy_server.global.response.BaseResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;

    // 회원가입
    public BaseResponse<?> postRegister(MemberRequestDTO dto) {

        if (memberRepository.existsById(dto.getId())) {
            throw new CustomException(CustomErrorCode.MEMBER_ALREADY_EXIST);
        }

        LanguageType languageType = LanguageType.fromString(dto.getLan());

        memberRepository.save(
                MemberEntity.builder()
                        .id(dto.getId())
                        .pw(bCryptPasswordEncoder.encode(dto.getPw()))
                        .lan(languageType) // 선택된 언어 설정
                        .build()
        );
        return BaseResponse.of(
                "OK",
                "회원가입 성공",
                null
        );
    }

    // 로그인
    public BaseResponse<?> postLogin(MemberRequestDTO dto) {
        MemberEntity member = memberRepository.findById(dto.getId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.MEMBER_NOT_EXIST));

        if (!bCryptPasswordEncoder.matches(dto.getPw(), member.getPw())) {
            throw new CustomException(CustomErrorCode.MEMBER_NOT_CORRECT);
        }

        return BaseResponse.of(
                "OK",
                "로그인 성공",
                List.of(
                        jwtUtils.generateToken(member)
                )
        );
    }


}