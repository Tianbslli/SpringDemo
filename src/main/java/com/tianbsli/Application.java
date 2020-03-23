package com.tianbsli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/21 10:19 下午
 */
@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
