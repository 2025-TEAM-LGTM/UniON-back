package com.union.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer roleId;

    @Column(name="field_id", nullable = false)
    private Long fieldId;

    @Column(name="role_name", nullable = false)
    private String roleName;

}
