package com.imooc.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    public CorsConfig() {

    }
    @Bean
    public CorsFilter corsFiler() {
        //1.添加cors配置信息
        CorsConfiguration configuration = new CorsConfiguration();
        //1.1添加允许跨域的内容
        //本地
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedOrigin("http://localhost:8088");
//        configuration.addAllowedOrigin("http://172.16.0.11:8080");
//        configuration.addAllowedOrigin("http://172.16.0.11:8088");
        //发布到服务器
        configuration.addAllowedOrigin("http://111.230.182.125:8080");
        configuration.addAllowedOrigin("http://111.230.182.125:8088");
        configuration.addAllowedOrigin("http://111.230.182.125:90");
        configuration.addAllowedOrigin("http://111.230.182.125:9001");

//        configuration.addAllowedOrigin("http://192.168.1.104:8088");
//        configuration.addAllowedOrigin("http://192.168.1.104:8080");


        //1.2设置是否发送cookie信息=》前端也会有=》（ form提交 		axios.defaults.withCredentials = true;）
        configuration.setAllowCredentials(true);
        //1.3设置语序请求方式
        configuration.addAllowedMethod("*");
        //1.4设置允许的header
        configuration.addAllowedHeader("*");

        //2.为url添加映射路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);//适用所有路由

        //3.返回重新定义好的corssource
        return new CorsFilter(source);
    }
}
