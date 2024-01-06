package com.example.User.Service.service;

import com.example.User.Service.entities.UserTable;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    UserTable saveUser(UserTable user);

    List<UserTable> getAllUser();

    UserTable getOneUser(String userId);

}
