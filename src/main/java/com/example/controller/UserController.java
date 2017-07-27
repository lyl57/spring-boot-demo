package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lyl57 on 2017/3/21.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository dao;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<?> user(@RequestBody User user){
        user=userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        return userService.get(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public ResponseEntity<?> list(User user){
        return new ResponseEntity<>(userService.findUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/test")
    public String test(User user){
        return "test";
    }

}
