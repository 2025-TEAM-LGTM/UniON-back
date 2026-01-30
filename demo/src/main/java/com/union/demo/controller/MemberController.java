package com.union.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    //1. 전체 팀원 목록 조회 + 필터링 /api/members?roleId=...&universityId=...&skillIds=1,2,3&p=...


    //2. 팀원 프로필 보기  /api/members/{memberId}
}
