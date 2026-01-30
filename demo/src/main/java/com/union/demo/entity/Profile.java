package com.union.demo.entity;

import com.union.demo.enums.Gender;
import com.union.demo.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {
    @Id
    @JoinColumn(name="user_id")
    private Long userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @JoinColumn(name="birth_year")
    private int birthYear;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="university_id")
    private University university;

    private String major;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int entrance_year;

}
