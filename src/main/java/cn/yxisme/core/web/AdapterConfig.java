package cn.yxisme.core.web;

import cn.yxisme.core.upload.UploadFileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by yangxiong on 2019/3/11.
 */
@Configuration
public class AdapterConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Autowired
    UploadFileConfig uploadFilePathConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(loginInterceptor());

        //排除配置
        addInterceptor.excludePathPatterns("/login/**");
        addInterceptor.excludePathPatterns("/upload/**");

        //拦截配置
        addInterceptor.addPathPatterns("/**/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler( "/upload/**")
                .addResourceLocations("file:" + uploadFilePathConfig.getUploadFolder());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
    }
}
