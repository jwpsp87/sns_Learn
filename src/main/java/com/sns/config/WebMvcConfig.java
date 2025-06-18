package com.sns.config;

import com.sns.common.FileManagerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

     // 예언된 이미지 경로와 서버에 업로드된 실제 파일과 매핑
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);        // 실제 파일 위치
    }

}
