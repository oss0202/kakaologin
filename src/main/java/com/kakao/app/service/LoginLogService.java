package com.kakao.app.service;

import com.kakao.app.domain.LoginLog;
import com.kakao.app.dto.LoginLogInsertRequestDto;
import com.kakao.app.repository.LoginLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LoginLogService {
    private final LoginLogRepository loginLogRepository;

    /**
     * 접속 로그 insert
     */
    public Long saveLoginLog(Long id){
        LoginLog loginLog = LoginLog.builder()
                .userId(id)
                .reqDt(LocalDateTime.now())
                .build();
        return loginLogRepository.save(loginLog).getId();
    }
}
