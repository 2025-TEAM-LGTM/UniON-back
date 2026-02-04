package com.union.demo.config;

import com.union.demo.jwt.JWTFilter;
import com.union.demo.jwt.JWTUtil;
import com.union.demo.jwt.LoginFilter;
import com.union.demo.repository.RefreshTokenRepository;
import com.union.demo.repository.UserRepository;
import com.union.demo.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

//CORS 설정
//jwt 관련 보안사항들

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    //Spring Security 내의 AuthenticationManager를 bean으로 등록
    //로그인 할 때 사용자의 인증(authentication)을 담당합니다.
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    //비밀번호 암호화
    //회원가입시 비밀번호를 안전하게 암호화하여 저장합니다.
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //cors 설정을 위한 bean 등록
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        return request -> {CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // 허용할 도메인
        configuration.setAllowedMethods(Collections.singletonList("*")); // 모든 HTTP 메서드 허용
        configuration.setAllowCredentials(true); // 인증 정보 포함 허용
        configuration.setAllowedHeaders(Collections.singletonList("*")); // 모든 헤더 허용
        configuration.setExposedHeaders(Collections.singletonList("Authorization")); // Authorization 헤더 노출
        configuration.setMaxAge(3600L); // 1시간 동안 캐싱
        return configuration;
        };

    }

    //Spring Security에서 요청이 들어올 때 거치는 보안 필터 흐름을 정의
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserRepository userRepository, RefreshTokenRepository refreshTokenRepository, RefreshTokenService refreshTokenService) throws Exception{
        LoginFilter loginFilter=new LoginFilter(authenticationManager(), jwtUtil, userRepository,refreshTokenService);
        loginFilter.setFilterProcessesUrl("/api/auth/login");

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) //cors 적용
                .csrf(csrf->csrf.disable())//jwt는 csrf 토큰을 사용하지 않음 -> 비활성화
                .formLogin(form -> form.disable())// security 자체 기본 로그인 폼 비활성화
                .httpBasic(httpBasic -> httpBasic.disable())//jwt 사용하니까 http basic 인증 비활성화

                //엔드포인트별 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/refresh","/api/auth/login","/api/members","/api/posts","/api/auth/signup","/api/auth/logout").permitAll() //누구나 접근 가능
                        .anyRequest().authenticated())//그 이외의 요청은 인증된 사용자만 접근이 가능

                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)

                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class)

                //세션 사용 x -> 세션 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build(); //지금까지 설정한 내용으로 SecurityFilterChain 객체 생성
    }

}
