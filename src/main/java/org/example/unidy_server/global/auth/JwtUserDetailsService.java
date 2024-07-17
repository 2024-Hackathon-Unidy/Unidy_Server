package org.example.unidy_server.global.auth;

import lombok.AllArgsConstructor;
import org.example.unidy_server.domain.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return memberRepository.findById(memberId)
                .map(JwtUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("ID: " + memberId + " 유저를 찾을 수 없습니다."));
    }
}
