package com.kakao.app.service;

import com.kakao.app.domain.User;
import com.kakao.app.dto.UserInsertRequestDto;
import com.kakao.app.dto.UserResponseDto;
import com.kakao.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * 이메일로 유저 조회
     */
    public UserResponseDto findByNm(String nm){
        User user = userRepository.findByNm(nm);

        return user != null ?
                UserResponseDto.builder()
                    .id(user.getId())
                    .nm(user.getNm())
                    .phone(user.getPhone())
                    .build()
                : null;
    }
    /**
     * 이름과 핸드폰번호로 회원가입
     */
    public Long saveUser(UserInsertRequestDto userInsertRequestDto){
        return userRepository.save(userInsertRequestDto.toEntity()).getId();
    }
}
