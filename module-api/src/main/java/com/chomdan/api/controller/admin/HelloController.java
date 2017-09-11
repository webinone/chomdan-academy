package com.chomdan.api.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by foresight on 17. 7. 24.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello Gradle Spring Boot";
    }

}
