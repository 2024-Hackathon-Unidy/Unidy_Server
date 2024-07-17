package org.example.unidy_server.global.auth;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@EqualsAndHashCode
@ToString
public class JwtInfo {

    private final String accessToken;
    private final String refreshToken;

    private JwtInfo(
            String accessToken,
            String refreshToken
    ) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static JwtInfo of(
            String accessToken,
            String refreshToken
    ) {
        if (accessToken == null || refreshToken == null) {
            throw new IllegalArgumentException("Tokens cannot be null");
        }
        return new JwtInfo(
                accessToken,
                refreshToken);
    }
}