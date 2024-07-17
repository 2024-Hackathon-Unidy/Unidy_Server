package org.example.unidy_server.global.auth;

import org.example.unidy_server.domain.member.entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public record JwtUserDetails(MemberEntity member) implements UserDetails {

    @Override
    public String getUsername() {
        return member.getId(); // 사용자 ID 반환
    }

    @Override
    public String getPassword() {
        return member.getPw(); // 비밀번호 반환
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 반환 로직 (필요시 구현)
        return Collections.emptyList(); // 권한이 필요 없으면 빈 리스트
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 여부
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠금 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격 증명 만료 여부
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 여부
    }
}