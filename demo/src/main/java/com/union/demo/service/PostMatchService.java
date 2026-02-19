package com.union.demo.service;

import com.union.demo.dto.request.PostMatchReqDto;
import com.union.demo.dto.response.MemberMatchResDto;
import com.union.demo.dto.response.PostMatchResDto;
import com.union.demo.dto.response.PostMatchUserDto;
import com.union.demo.entity.Users;
import com.union.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostMatchService {

    //1. getMatching 팀원 추천받기 + 필터링
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public MemberMatchResDto postMatchFastApi(Long postId) {
        String url = "http://localhost:8000/match_result";
        PostMatchReqDto req = new PostMatchReqDto(postId);

        PostMatchResDto fastRes;
        try{
            fastRes=restTemplate.postForObject(url, req, PostMatchResDto.class);
        } catch (RestClientException e){
            return MemberMatchResDto.builder()
                    .members(List.of())
                    .build();
        }

        if(fastRes == null || fastRes.getResults()==null || fastRes.getResults().isEmpty()){
            return MemberMatchResDto.builder()
                    .members(List.of())
                    .build();
        }

        List<PostMatchUserDto> results=fastRes.getResults();

        //fastApi가 준 userId 순서 그대로 유지하도록
        List<Long> userIds= results.stream()
                .map(PostMatchUserDto::getUserId)
                .filter(Objects::nonNull)
                .toList();

        if(userIds.isEmpty()){
            return MemberMatchResDto.builder()
                    .members(List.of())
                    .build();
        }

        //userId -> mainStrength 매핑
        Map<Long, String> strengthMap=results.stream()
                .filter(r -> r.getUserId()!=null)
                .collect(Collectors.toMap(
                        PostMatchUserDto::getUserId,
                        PostMatchUserDto::getMainStrength,
                        (a,b ) -> a,// 중복 id가 들어오면 첫번째 값으로
                        LinkedHashMap::new
                ));

        //Users 한번에 조회
        List<Users> users= userRepository.findAllByUserIdInWithDetail(userIds);

        //userId -> Users 매핑
        Map<Long, Users> userMap=users.stream()
                .collect(Collectors.toMap(Users::getUserId, Function.identity()));

        //fastAPI 순서대로 최종 dto 구성
        List<MemberMatchResDto.MemberMatchDto> members=userIds.stream()
                .map(userMap::get)
                .filter(Objects::nonNull)
                .map(u-> MemberMatchResDto.from(u, strengthMap.get(u.getUserId())))
                .toList();

        return MemberMatchResDto.builder()
                .members(members)
                .build();

    }


}
