package com.chomdan.api.config;

import com.chomdan.shared.http.interceptor.TokenAdminInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource(value={
    "classpath:property/api-config.properties"
})
@EnableWebMvc
public class ApiConfig extends WebMvcConfigurerAdapter {

    @Value( "${api.upload.file.path}" )
    private String staticsFilePath;

    // resource handler
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // super.addResourceHandlers(registry);
        registry.addResourceHandler("/statics/**")
                .addResourceLocations("file:"+staticsFilePath);
    }

    // @CrossOrigin과 함께 쓸 수 있다.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(false)
                .maxAge(3600);
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);

        registry.addInterceptor(createTokenAdminInterceptor()).addPathPatterns("/api/**/admin/*");
    }

    @Bean
    public TokenAdminInterceptor createTokenAdminInterceptor() {
        return new TokenAdminInterceptor();
    }
}