package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

//启动类
//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//扫描mybatis 通用mapper所在的包
@MapperScan(basePackages = "com.imooc.mapper")
//开启事务管理 可加可不加
@EnableTransactionManagement
//扫描所有包以及相关组件包
@ComponentScan(basePackages = {"com.imooc","org.n3r.idworker"})
@EnableScheduling //开启定时任务
@EnableRedisHttpSession  // 开启使用redis作为spring session  ,还需要将 @SpringBootApplication改为
// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) =》去掉每次进入springsession还需要登录
// 但是使用这个耦合度高，不建议使用springsession  目前系统就   @GetMapping("/setSession")用到
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}