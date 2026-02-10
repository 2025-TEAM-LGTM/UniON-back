package com.union.demo.dto.response;

import com.union.demo.entity.Role;
import com.union.demo.entity.Users;
import com.union.demo.enums.PersonalityKey;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter@Builder
@AllArgsConstructor @NoArgsConstructor(access= AccessLevel.PROTECTED)
public class MemberListResDto {
    private List<MemberDto> members;

    @Getter@Builder
    @AllArgsConstructor @NoArgsConstructor(access= AccessLevel.PROTECTED)
    public static class MemberDto{

        private Long userId;
        private String username;
        private String profileImageUrl;
        private RoleDto role;
        private List<HardSkillDto> hardSkill;
        private Map<PersonalityKey, Integer> personality;
    }

    @Getter@Builder
    @AllArgsConstructor @NoArgsConstructor(access= AccessLevel.PROTECTED)
    public static class RoleDto{
        private Integer roleId;
        private String roleName;
    }

    @Getter@Builder
    @AllArgsConstructor @NoArgsConstructor(access= AccessLevel.PROTECTED)
    public static class HardSkillDto{
        private Integer hardSkillId;
        private String hardSkillName;
    }

    //mapping
    public static MemberDto from(
            Users user,
            List<HardSkillDto> skills
    ){
        Role r=user.getMainRoleId();

        return MemberDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .profileImageUrl(user.getImage().getImageUrl())
                .role(RoleDto.builder()
                        .roleId(r.getRoleId())
                        .roleName(r.getRoleName())
                        .build())
                .hardSkill(user.getUserSkills().stream()
                        .map(skill -> HardSkillDto.builder()
                                .hardSkillId(skill.getSkill().getSkillId())
                                .hardSkillName(skill.getSkill().getSkillName())
                                .build()
                        ).toList())
                .personality(user.getPersonality())
                .build();

    }



}
