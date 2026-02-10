package com.union.demo.controller;

import com.union.demo.dto.response.MemberListResDto;
import com.union.demo.enums.PersonalityKey;
import com.union.demo.global.common.ApiResponse;
import com.union.demo.service.MemberService;
import com.union.demo.utill.PersonalityParserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    //1. 전체 팀원 목록 조회 + 필터링 /api/members?roleId=...&universityId=...&skillIds=1,2,3&p=...
    @GetMapping
    public ResponseEntity<ApiResponse<MemberListResDto>> getMembers(
            @RequestParam(required = false) List<Integer> roleId,
            @RequestParam(required = false) List<Integer> hardSkillId,
            @RequestParam(required = false) String personality
            ){
        Map<PersonalityKey, Integer> personalityFilter = PersonalityParserUtil.parse(personality);
        MemberListResDto data=memberService.getMembers(roleId, hardSkillId, personalityFilter);

        return ResponseEntity.ok(ApiResponse.ok(data));

        }

    //2. 팀원 프로필 보기  /api/members/{memberId}
}
