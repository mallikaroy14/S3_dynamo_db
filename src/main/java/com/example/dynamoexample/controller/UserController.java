package com.example.dynamoexample.controller;

import com.example.dynamoexample.models.Users;
import com.example.dynamoexample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public Users create(@RequestBody Users users) {
        return userService.create(users);
    }

    @GetMapping("/")
    public List<Users> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Users get(@PathVariable String id){
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){
        return userService.delete(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody Users users){
        return userService.update(users, id);
    }
}
