package com.union.demo.service;

import com.union.demo.dto.response.MyProfileResDto;
import com.union.demo.entity.Profile;
import com.union.demo.entity.Users;
import com.union.demo.repository.ProfileRepository;
import com.union.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    //1. getProfile 프로필 조회
    public MyProfileResDto getMyProfile(Long userId){
        //유저 존재여부 확인
        Users user =userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 userId입니다."));

        //user가 profile을 가지고 있는지 확인
        Profile profile  =profileRepository.findByUserId(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 profile입니다."));


        //return
        return MyProfileResDto.from(
                user, profile
        );

    }

    //2. updateProfile 프로필 수정

    //3. getPortfolios 포폴 목록 조회

    //4. postPortfolio 포폴 업로드

    //5. updatePortfolio 포폴 수정

    //6. deletePortfolio 포폴 삭제

    //7. getDetailPortfolio 세부 포폴 페이지 조회
}
