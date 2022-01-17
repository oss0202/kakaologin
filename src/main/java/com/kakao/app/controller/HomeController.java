package com.kakao.app.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.kakao.app.KakaoAPI;
import com.kakao.app.dto.UserInsertRequestDto;
import com.kakao.app.dto.UserResponseDto;
import com.kakao.app.repository.LoginLogRepository;
import com.kakao.app.service.LoginLogService;
import com.kakao.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
public class HomeController {
    KakaoAPI kakaoApi = new KakaoAPI();

    final UserService userService;

    final LoginLogService loginLogService;

    @GetMapping(value="/login")
    public ModelAndView login(@RequestParam("code") String code, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        // 1번 인증코드 요청 전달
        String accessToken = kakaoApi.getAccessToken(code);
        // 2번 인증코드로 토큰 전달
        HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);

        System.out.println("login info : " + userInfo.toString());

        /**
         * 회원가입한 유저인지 확인
         */
        UserResponseDto userResponseDto = userService.findByNm(String.valueOf(userInfo.get("nickname")));

        if(userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("nickname", userInfo.get("nickname"));
            session.setAttribute("accessToken", accessToken);
        }


        if( userResponseDto != null){
            mav.setViewName("redirect:/main");
        }else{
            mav.addObject("userId", userInfo.get("email"));
            mav.addObject("nickname", userInfo.get("nickname"));
            mav.addObject("userResponseDto", userResponseDto);
            mav.setViewName("index");
        }
        return mav;
    }

    @GetMapping(value="/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));
        session.removeAttribute("accessToken");
        session.removeAttribute("userId");
        mav.setViewName("index");
        return mav;
    }

    @PostMapping(value="/join", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Long> saveUser(@RequestBody UserInsertRequestDto userInsertRequestDto) {
        Long id = userService.saveUser(userInsertRequestDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping(value = "/main")
    public ModelAndView main(HttpSession session){
        ModelAndView mav = new ModelAndView();
        /**
         * 회원가입한 유저인지 확인
         */
        UserResponseDto userResponseDto = userService.findByNm(String.valueOf(session.getAttribute("nickname")));

        /**
         * 유저 접속 기록 저장
         */
        loginLogService.saveLoginLog(userResponseDto.getId());

        mav.addObject("userResponseDto", userResponseDto);
        mav.setViewName("main");
        return mav;
    }
}
