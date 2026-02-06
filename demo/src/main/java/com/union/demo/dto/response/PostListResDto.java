package com.union.demo.dto.response;

import com.union.demo.entity.Domain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostListResDto {

    private List<PostSummaryDto> posts;


    @Getter
    @AllArgsConstructor
    @Builder
    public static class PostSummaryDto{
        private Long postId;
        private String title;
        private Integer dday;
        private List<Integer> domainIds; //primeDomainId, secondDomainId
        private List<RecruitDto> recruits;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class RecruitDto{
        private Integer roleId;
        private String roleName;
        private Integer roleCount;
    }

}
