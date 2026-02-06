package com.union.demo.service;

import com.union.demo.dto.response.PostListResDto;
import com.union.demo.entity.Domain;
import com.union.demo.entity.Post;
import com.union.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    //getAllPosts 전체 공고 목록 조회 * 공고 필터기능
    private final PostRepository postRepository;

    public PostListResDto getAllPosts(List<Integer> domainIds, List<Integer> fieldIds, List<Integer> roleIds){
        //빈 리스트 처리
        domainIds=normalize(domainIds);
        fieldIds=normalize(fieldIds);
        roleIds=normalize(domainIds);


        var posts=postRepository.findAllWithInfoAndRecruitRoles( domainIds,  fieldIds,  roleIds);

        var postDtos=posts.stream().map(p-> {
            //D-day 계산
            int dday=calcDday(p.getPostInfo().getRecruitEdate());
            //만약 dday 입력 안되었을 때 처리
            //int dday = calcDday(p.getPostInfo() != null ? p.getPostInfo().getRecruitEdate() : null);

            //recruits 정보 빌드
            var recruits= p.getRecruitRoles().stream()
                    .map(prr -> PostListResDto.RecruitDto.builder()
                            .roleId(prr.getRole().getRoleId())
                            .roleName((prr.getRole().getRoleName()))
                            .roleCount((prr.getCount())) //만약 count가 없을 때 prr.getCount() == null ? 0 : prr.getCount()
                            .build()
                    )
                    .toList();

            //domainIds 만들기
            var domainIdList=toDomainIds(p);

            //postList 데이터들 빌드
            return PostListResDto.PostSummaryDto.builder()
                    .postId(p.getPostId())
                    .title(p.getTitle())
                    .dday(dday)
                    .domainIds(domainIdList)
                    .recruits(recruits)
                    .build();
        }).toList();

        return PostListResDto.builder()
                .posts(postDtos)
                .build();
    }
    //빈 리스트면 null 처리
    private List<Integer> normalize(List<Integer> ids) {
        return (ids == null || ids.isEmpty()) ? null : ids;
    }
    //D-day 계산
    private int calcDday(OffsetDateTime recruitEdate){
        if(recruitEdate == null) return 0;
        return (int) ChronoUnit.DAYS.between(OffsetDateTime.now(), recruitEdate);
    }

    //domainId 가져오기
    private List<Integer> toDomainIds(Post post){
        return java.util.stream.Stream.of(post.getPrimeDomainId(),post.getSecondDomainId())
                .filter(java.util.Objects :: nonNull)
                .map(Domain::getDomainId)
                .distinct()
                .toList();
    }



    //2. createPost 공고 작성

    //3. updatePost 공고 수정

    //4. deletePost 공고 삭제

    //5. getDetailPost 공고 상세페이지 + 공고명 조회



}
