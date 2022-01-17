package com.kakao.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String nm;
    private String phone;

    @Builder
    public UserResponseDto(Long id, String nm, String phone) {
        this.id = id;
        this.nm = nm;
        this.phone = phone;
    }
}
