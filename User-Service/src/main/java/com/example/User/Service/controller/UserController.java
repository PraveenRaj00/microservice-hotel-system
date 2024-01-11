package com.example.User.Service.controller;

import com.example.User.Service.entities.UserTable;
import com.example.User.Service.service.UserService;
import com.netflix.discovery.converters.Auto;
import jakarta.ws.rs.Path;
import lombok.Getter;
import org.hibernate.sql.model.PreparableMutationOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<UserTable> saveUser(@RequestBody UserTable userTable){
        UserTable user= service.saveUser(userTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @GetMapping
    public ResponseEntity<List<UserTable>> getAllUser(){
        List<UserTable> allUsers= service.getAllUser();
        return ResponseEntity.ok(allUsers);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserTable> getOneUser(@PathVariable String userId){

        return ResponseEntity.ok(service.getOneUser(userId));
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<UserTable> updateUserEmail(@RequestBody String email, @PathVariable String userId){

        return ResponseEntity.ok(service.updateUserEmail(email,userId));
    }

    @PatchMapping("/partial-update/{userId}")
    public ResponseEntity<UserTable> updatePartialField(@PathVariable String userId, @RequestBody Map<String, Object> fields){
        return ResponseEntity.ok(service.updatePartialField(userId,fields));
    }
}
