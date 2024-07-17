package org.example.unidy_server.domain.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.unidy_server.domain.member.entity.MemberEntity;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponseDTO {

    private String id;

    public MemberResponseDTO of (MemberEntity member) {
        return MemberResponseDTO.builder()
                .id(member.getId())
                .build();
    }
}
