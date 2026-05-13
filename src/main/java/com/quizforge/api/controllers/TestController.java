package com.quizforge.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public/hello")
    public String hello() {
        return "Hello this is public path!";
    }

    @GetMapping("/private/hello")
    public String privateHello() {
        return "Hii, you are VIP";
    }
}
