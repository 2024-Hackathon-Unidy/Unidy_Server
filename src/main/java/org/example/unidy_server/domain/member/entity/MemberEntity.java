package org.example.unidy_server.domain.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.unidy_server.domain.member.model.enums.LanguageType;


@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class MemberEntity {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    // 회원 id
    @Column(name = "id", nullable = false)
    private String id;

    // 회원 비밀번호
    @Column(name = "pw", nullable = false)
    private String pw;

    // 사용 언어
    @Column(name = "lan", nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageType lan;


    @Builder
    public MemberEntity(
            Long idx,
            String id,
            String pw,
            LanguageType lan
    ) {
        this.idx = idx;
        this.id = id;
        this.pw = pw;
        this.lan = lan;
    }
}
