package com.union.demo.entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, unique = true, length =50)
    private String login_id;

    @Column(nullable = false, length =255)
    private String password;

    @Column(nullable = false, length =50)
    private String username;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Role role;

    @Column(columnDefinition = "json") //json 타입으로 받기
    private String personality;
}
