package com.example.springredis.part7.controller;

import com.example.springredis.part7.dto.UserProfile;
import com.example.springredis.part7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    //Ch 07. 서비스 속도를 높이는 캐시 레이어 만들기기

    //   스프링 자체 캐시 사용 하려면
    //   1. application.yml 에서 cache: type: redis  추가
    //   2. SpringRedisApplication 에 @EnableCaching 추가
    //   3. 대상 메서드에 @Cacheable(cacheNames = "userAgeCahe", key = "#userId") 추가
    //      cacheNames 는 하고싶은 이름 정하고 key 는 변수 이름


   @Autowired
    UserService userService;

    @GetMapping("/users/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable String userId) throws InterruptedException {
        return userService.getUserProfile(userId);
    }


    /*
     *
     * @Cacheable: 메서드의 결과를 캐시에 저장하고, 이후 동일한 매개변수로 호출될 때 캐시에서 결과를 가져옵니다.메서드가 호출되기 전에 캐시에 결과가 있는지 확인하고, 있으면 해당 결과를 반환하며, 없으면 메서드를 실행하고 결과를 캐시에 저장합니다.
     *
     * @CachePut: 메서드의 결과를 강제로 캐시에 저장합니다. 이 애너테이션이 적용된 메서드는 항상 실행되고, 실행 결과가 캐시에 저장됩니다. 따라서 메서드의 결과를 갱신하거나 강제로 캐시에 저장할 때 사용합니다.
     *
     * @CacheEvict: 캐시에서 데이터를 제거합니다. 특정 메서드를 호출하면 캐시에서 해당 데이터가 삭제됩니다. 보통 데이터를 갱신하거나 삭제할 때 사용합니다.
     */

}
