package com.example.springredis.part6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    // Ch 06. 분산 환경에서의 세션 스토어 만들기

    // 1.
    // 'org.springframework.session:spring-session-data-redis'
    // 라이브러리를 프로젝트에 추가하면 Spring Session 의 Redis 지원을 활성화할 수 있습니다.
    // 이를 통해 Spring Security 와 함께 사용하여 세션 관리를 개선하고,
    // 분산 환경에서 세션 상태를 공유할 수 있습니다


    // 2. application.yml에서  store-type: redis 추가

    // 3 . 세션 관리를 레디스로 변경하면 쿠키 저장소 대신 레디스 저장소가 사용되며,
    // 세션 식별자(Session ID)는 클라이언트의 쿠키 저장소 대신 레디스에 저장됩니다
    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
        session.setAttribute("name", name);
        return "saved.";
    }

    @GetMapping("/myName")
    public String myName(HttpSession session) {
      String myName = (String) session.getAttribute("name");
        return myName;
    }
}
