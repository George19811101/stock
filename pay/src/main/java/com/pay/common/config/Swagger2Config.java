package com.pay.common.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * Swagger2配置
 * @author
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class Swagger2Config implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/swagger-ui/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/3.45.0/");
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.xxxx.xxx"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API文档").version("1.0").build();
    }
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof MappingJackson2HttpMessageConverter);
//        converters.add(gsonHttpMessageConverters());
//    }

    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverters() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter())
                .create();
        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter(gson);
        return gsonConverter;
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
                .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter())
                .create();
        converters.add(new GsonHttpMessageConverter(gson));
//        super.configureMessageConverters(converters);
//        super.configureMessageConverters(converters);
    }

}