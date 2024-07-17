package org.example.unidy_server.domain.member.dto;

public class MemberInfo {
    private final String id; // 사용자 ID
    private final String password;

    public MemberInfo(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}