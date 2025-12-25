package com.example.demo.controller;

import com.example.demo.dto.HelloDto;
import com.example.demo.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    //add a param name to the endpoint
    @GetMapping("/hello")
    public HelloDto hello(@RequestParam("name") String name) {
        return helloService.getHello(name);
    }
}
