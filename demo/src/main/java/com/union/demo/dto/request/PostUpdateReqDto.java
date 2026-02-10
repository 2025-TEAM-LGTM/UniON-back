package com.union.demo.dto.request;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter@Setter
@NoArgsConstructor
public class PostUpdateReqDto {
    String title;
    List<Integer> domainIds;
    private RecruitPeriodDto recruitPeriod;
    private String homepageUrl;
    private String contact;

    private List<RoleCountDto> currentRoles;

    private List<RoleCountDto> recruitRoles;

    private String seeking;
    private String aboutUs;
    private Map<String, Integer> teamCulture;
    private String imageUrl;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class RecruitPeriodDto{
        private LocalDate startDate;
        private LocalDate endDate;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class RoleCountDto{
        private Integer roleId;
        private Integer count;
    }

}
