package com.kakao.app.dto;

import com.kakao.app.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class UserInsertRequestDto {
    private String nm;
    private String phone;

    public User toEntity(){
        return User.builder()
                .nm(nm)
                .phone(phone)
                .build();
    }
}
