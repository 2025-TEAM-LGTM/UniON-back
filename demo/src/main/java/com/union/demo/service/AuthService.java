package com.union.demo.service;
//jwt 기준
public class AuthService {
    //1. signup
        //1-1 loginId/email/nickname 중복 체크
        //1-2 비밀번호 해싱(BCrypt)
        //1-3 User 저장 + 기본 Profile 생성(선택)



    //2. login
        //2-1 아이디/ 비번 검증
        //2-2 AccessToken 발급(짧게)
        //2-3 RefreshToken 발급(길게)
        //2-4 RefreshToken을 db에 저장

    //3. logout
        //3-1 db에서 refreshToken 삭제
        //3-2 access는 만료될때까지 유효

    //4. refresh: 재발급
        //4-1 db에서 현재 유효한 refresh 인지 확인
        //4-2 새로운 access 발급
        //4-3refresh rotation(새 retresh도 발급하고 기존 refresh 폐기)

    //5. 닉네임 중복 체크
}
