package com.union.demo.dto.response;

import com.union.demo.entity.Role;
import com.union.demo.entity.Users;
import com.union.demo.enums.PersonalityKey;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class MemberMatchResDto {
    //프론트에 보낼 res dto

    private List<MemberMatchDto> members;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access=AccessLevel.PROTECTED)
    public static class MemberMatchDto{
        private Long userId;
        private String username;
        private String profileImageUrl;
        private ItemDto role;
        private List<ItemDto> hardSkill;
        private Map<PersonalityKey, Integer> personality;
        private String mainStrength;
    }


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access=AccessLevel.PROTECTED)
    public static class ItemDto{
        private Integer id;
        private String name;
    }

    public static MemberMatchDto from(Users user, String mainStrength){
        Role r=user.getMainRoleId();

        return MemberMatchDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .profileImageUrl(user.getImage()!=null?user.getImage().getImageUrl():null)
                .role(ItemDto.builder()
                        .id(r.getRoleId())
                        .name(r.getRoleName())
                        .build())
                .hardSkill(user.getUserSkills().stream()
                        .map(us-> ItemDto.builder()
                                .id(us.getSkill().getSkillId())
                                .name(us.getSkill().getSkillName())
                                .build())
                        .toList())
                .personality(user.getPersonality())
                .mainStrength(mainStrength)
                .build();
    }
}
