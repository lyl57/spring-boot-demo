package com.example.controller;

import com.example.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lyl57 on 2017/3/13.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/")
    public String test1(){
        return "Hello World";
    }

    @RequestMapping("/{name}")
    public ResponseEntity<?> testName(@PathVariable String name){
        User user = new User();
        user.setName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
