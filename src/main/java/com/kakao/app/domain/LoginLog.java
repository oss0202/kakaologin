package com.kakao.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class LoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private LocalDateTime reqDt;

    @Builder
    public LoginLog(Long id, Long userId, LocalDateTime reqDt) {
        this.id = id;
        this.userId = userId;
        this.reqDt = reqDt;
    }
}
