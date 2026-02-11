package com.union.demo.controller;

import com.union.demo.dto.request.PostCreateReqDto;
import com.union.demo.dto.request.PostUpdateReqDto;
import com.union.demo.dto.response.PostDetailResDto;
import com.union.demo.dto.response.PostListResDto;
import com.union.demo.dto.response.PostPageResDto;
import com.union.demo.entity.Users;
import com.union.demo.global.common.ApiResponse;
import com.union.demo.repository.UserRepository;
import com.union.demo.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    //1. 전체 공고 목록 조회 + 쿼리 검색
    private final PostService postService;
    private final UserRepository userRepository;

    @Operation(summary = "전체 공고 목록 조회", description = "전체 목록 조회 + 쿼리를 통해 검색도 가능, 자세한 쿼리내용은 노션 api 문서 확인하기")
    @GetMapping
    public ResponseEntity<ApiResponse<PostListResDto>> getPosts(
            @RequestParam(required = false) List<Integer> domainIds,
            @RequestParam(required = false) List<Integer> fieldIds,
            @RequestParam(required = false) List<Integer> roleIds

    ){
        PostListResDto data= postService.getAllPosts(domainIds, fieldIds, roleIds);
        return ResponseEntity.ok(ApiResponse.ok(data));
    }

    //2. 공고 작성 기능 "/api/posts"
    @PostMapping
    public ResponseEntity<PostDetailResDto> createPost(
            @AuthenticationPrincipal Long userId,
            @RequestBody @Valid PostCreateReqDto req
            ){
        Users leader=userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
        Long leaderId=leader.getUserId();

        PostDetailResDto res=postService.createPost(leaderId,req);
        return ResponseEntity.ok(res);
    }

    //3. 공고 수정하기 "/api/posts/{postId}"
    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostDetailResDto>> updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateReqDto req
            ){
        PostDetailResDto data= postService.updatePost(postId, req);
        return ResponseEntity.ok(ApiResponse.ok(data));

    }

    //4. 공고 삭제하기 "/api/posts/{postId}"
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Long>> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(ApiResponse.ok(postId));
    }

    //5. 공고 상세 페이지 + 공고명 보기 "/api/posts/{postId}"
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostPageResDto>> getPostDetail(@PathVariable Long postId){
        PostPageResDto data= postService.getPostDetail(postId);
        return ResponseEntity.ok(ApiResponse.ok(data));
    }

}

