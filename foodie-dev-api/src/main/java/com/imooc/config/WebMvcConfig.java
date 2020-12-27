package com.imooc.config;

import com.imooc.controller.interceptor.UserTokenInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //    WebMvcConfigurer是一个接口，提供很多自定义的拦截器，例如跨域设置、类型转化器等等。
//    可以说此接口为开发者提前想到了很多拦截层面的需求，方便开发者自由选择使用。由于Spring5.0
//    废弃了WebMvcConfigurerAdapter，所以WebMvcConfigurer继承了WebMvcConfigurerAdapter大部分内容。
    // 实现静态资源的映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/META-INF/resources/")  // 映射swagger2
                .addResourceLocations("file:/workspaces/images/");  // 映射本地静态资源=>头像保存地址
        //如果资源路径workspaces/images/foodie/faces/200804FXY71W7168/face-200804FXY71W7168.jpg
        //那么访问时候就是 端口号:foodie/faces/200804FXY71W7168/face-200804FXY71W7168.jpg=>http://localhost:8088/foodie/faces/200804FXY71W7168/face-200804FXY71W7168.jpg
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    //使用拦截器之前，先bean 拦截器的class 比如 UserTokenInterceptor
    @Bean
    public UserTokenInterceptor userTokenInterceptor() {
        return new UserTokenInterceptor();
    }
    /**
     * 注册拦截器
     * 使用拦截器之前，先bean 拦截器的class 比如 UserTokenInterceptor
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       //添加需要拦截的方法
        registry.addInterceptor(userTokenInterceptor())
                .addPathPatterns("/hello")
                .addPathPatterns("/shopcart/add")
                .addPathPatterns("/shopcart/del")
                .addPathPatterns("/address/list")
                .addPathPatterns("/address/add")
                .addPathPatterns("/address/update")
                .addPathPatterns("/address/setDefalut")
                .addPathPatterns("/address/delete")
                .addPathPatterns("/orders/*")
                .addPathPatterns("/center/*")
                .addPathPatterns("/userInfo/*")
                .addPathPatterns("/myorders/*")
                .addPathPatterns("/mycomments/*")
                .excludePathPatterns("/myorders/deliver")
                .excludePathPatterns("/orders/notifyMerchantOrderPaid");
        //添加到注册器
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
