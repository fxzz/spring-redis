package com.example.springredis.part5;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

   private final StringRedisTemplate redisTemplate;

   //CH 05. Redis 개발 실습

   @GetMapping("/setFruit")
    public String setFruit(@RequestParam String name) {
       ValueOperations<String, String> ops = redisTemplate.opsForValue();
       ops.set("fruit", name);

       return "saved.";
    }

    @GetMapping("/getFruit")
    public String getFruit() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String fruitName = ops.get("fruit");

        return fruitName;
    }
}
