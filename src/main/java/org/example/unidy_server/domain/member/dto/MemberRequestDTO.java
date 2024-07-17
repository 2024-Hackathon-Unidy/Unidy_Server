package org.example.unidy_server.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.example.unidy_server.domain.member.model.enums.LanguageType;

@Getter
public class MemberRequestDTO {

    @NotBlank
    private String id;

    @NotBlank
    private String pw;

    @NotBlank
    private String lan;
}
