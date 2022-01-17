package com.kakao.app.dto;

import com.kakao.app.domain.LoginLog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
public class LoginLogInsertRequestDto {
    private Long userId;
    private LocalDateTime reqDt;

    public LoginLog toEntity(){
        return LoginLog.builder()
                .userId(userId)
                .reqDt(LocalDateTime.now())
                .build();
    }
}
