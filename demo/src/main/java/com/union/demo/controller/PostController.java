package com.union.demo.controller;

import com.union.demo.dto.response.PostListResDto;
import com.union.demo.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    //1. 전체 공고 목록 조회 + 쿼리 검색
    private final PostService postService;
    @Operation(summary = "전체 공고 목록 조회", description = "전체 목록 조회 + 쿼리를 통해 검색도 가능")
    @GetMapping
    public PostListResDto getPosts(
            @RequestParam(required = false) List<Integer> domainIds,
            @RequestParam(required = false) List<Integer> fieldIds,
            @RequestParam(required = false) List<Integer> roleIds

    ){
        return postService.getAllPosts(domainIds, fieldIds, roleIds);
    }




    //2. 공고 작성 기능 "/api/posts"


    //3. 공고 수정하기 "/api/posts/{postId}"


    //4. 공고 삭제하기 "/api/posts/{postId}"


    //5. 공고 상세 페이지 + 공고명 보기 "/api/posts/{postId}"







}

