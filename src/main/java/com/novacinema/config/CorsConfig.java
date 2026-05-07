// 예시: com.novacinema.config.CorsConfig.java
package com.novacinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 프론트엔드와 백엔드 간의 교차 출처 리소스 공유(CORS) 정책을 설정하는 구성 파일
// 로컬 개발 환경(포트 3000, 5175 등)에서의 API 요청을 허용하여 원활한 통신을 보장한다
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 API 경로에 적용
                        .allowedOriginPatterns("http://localhost:3000","http://localhost:5175")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        // 자격 증명(쿠키, 인증 헤더 등)을 포함한 요청을 허용하기 위한 필수 설정
                        .allowCredentials(true);
            }
        };
    }
}