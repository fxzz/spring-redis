package com.example.springredis.part7.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

    public String getUserName(String userId) throws InterruptedException {
        // DB 호출했는데 3초 지연된다는 가정 , 케싱 효과를 보기 위해

        Thread.sleep(3000);

        System.out.println("----------------");

        if (userId.equals("A")) {
            return "Adam";
        }

        if (userId.equals("B")) {
            return "Bob";
        }

        return "";
    }

    @Cacheable(cacheNames = "userAgeCahe", key = "#userId")
    public int getUserAge(String userId) throws InterruptedException {

        Thread.sleep(1000);

        System.out.println("----------------");

        if (userId.equals("A")) {
            return 28;
        }

        if (userId.equals("B")) {
            return 32;
        }

        return 0;
    }
}
