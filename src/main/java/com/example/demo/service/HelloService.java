package com.example.demo.service;

import com.example.demo.dto.HelloDto;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public HelloDto getHello(String name) {
        return new HelloDto("Hello, " + name + "!");
    }
}
