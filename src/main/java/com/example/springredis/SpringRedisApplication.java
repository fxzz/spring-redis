package com.example.springredis;

import com.example.springredis.part9.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringRedisApplication implements CommandLineRunner {

    @Autowired
    private ChatService chatService;

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start");

        chatService.enterCharRoom("chat1");
        //어플리케이션 실행 시
        //chatService.enterCharRoom("chat1");를 실행 시켜서 활성화 된다
    }


    /*
    *
    * CommandLineRunner 인터페이스를 구현한 run 메서드는 스프링 부트
    * 애플리케이션 실행 시 한 번 호출되는 메서드입니다
    * */
}
