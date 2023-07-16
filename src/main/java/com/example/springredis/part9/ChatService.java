package com.example.springredis.part9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ChatService implements MessageListener {

    @Autowired
    @Qualifier("redisContainer")
    private RedisMessageListenerContainer container;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void enterCharRoom(String chatRoomName) {
        container.addMessageListener(this, new ChannelTopic(chatRoomName));

        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("q")) {
                System.out.println("Quit..");
                break;
            }

            redisTemplate.convertAndSend(chatRoomName, line);
            //사용자가 한줄 입력할때마다 chatRoomName 토픽으로 메세지 전송
        }

        container.removeMessageListener(this);
        //나갈때 메세지 삭제
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message: " + message.toString());
        // Override 하면 레디스 sub을 통해서 도착한 메세지를 확인 할수 있다
        // 메세지가 도착 할때마다 화면에 출력
    }
}

/*
* 사용 방법 그레들 > 빌드 눌러서 만들고
* 터미널 실행 해서 build/libs 로 가서
* java -jar spring-redis-0.0.1-SNAPSHOT.jar 실행 (다른 포트 실행 하고 싶다면 java -jar spring-redis-0.0.1-SNAPSHOT.jar --server.port=8081)
* CommandLineRunner 로 인해 챗룸에 바로 접속 했다면 레디스 터미널 가서 redis-cli 로 접속후
* publish chat1 hi 하면 됨
*
* 1. SpringRedisApplication 에서 implements CommandLineRunner를 추가한다
*
* 2. RedisConfig 에서 redisContainer 를 등록한다
*
* 3. ChatService 작성 후 SpringRedisApplication 로 가서 chatService를 사용해 채팅방을 활성화 시킨다
*
*
* CommandLineRunner 는 CommandLineRunner 인터페이스를 구현한 run 메서드는 스프링 부트 애플리케이션 실행 시 한 번 호출되는 메서드 이다
* 사용 이유는 뷰를 안만들었기 때문에 터미널에서 간단하게 실행 하려고 이 방법으로 사용함
* */