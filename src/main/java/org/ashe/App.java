package org.ashe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主程序启动类
 * By Ashe
 */
@SpringBootApplication
@MapperScan({"org.ashe.mapper", "org.ashe.repository"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
