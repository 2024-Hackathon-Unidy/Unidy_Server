package org.example.unidy_server.domain.member.model.enums;

public enum LanguageType {
    KO("ko"), // 한국어
    EN("en"), // 영어
    JP("jp"), // 일본어
    CH("ch"); // 중국어

    private final String value;

    LanguageType(String value) {
        this.value = value;
    }

    public static LanguageType fromString(String language) {
        for (LanguageType lang : LanguageType.values()) {
            if (lang.value.equalsIgnoreCase(language)) {
                return lang;
            }
        }
        throw new IllegalArgumentException("Invalid language type: " + language);
    }
}