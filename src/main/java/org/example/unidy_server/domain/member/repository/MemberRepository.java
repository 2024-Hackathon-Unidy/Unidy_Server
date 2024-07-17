package org.example.unidy_server.domain.member.repository;

import org.example.unidy_server.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findById(String memberId);
    boolean existsById(String idx);
}
