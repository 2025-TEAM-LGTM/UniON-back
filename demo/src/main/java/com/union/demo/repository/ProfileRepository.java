package com.union.demo.repository;

import com.union.demo.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    //userId로 프로필 조회
    Optional<Profile> findByUserId(Long usersId);

    //프로필 존재 여부 확인
    boolean existsByUser_UserId(Long userId);

}
