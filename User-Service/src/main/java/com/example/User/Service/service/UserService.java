package com.example.User.Service.service;

import com.example.User.Service.entities.UserTable;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.List;
import java.util.Map;

public interface UserService {

    UserTable saveUser(UserTable user);

    List<UserTable> getAllUser();


    UserTable getOneUser(String userId);

    UserTable updateUserEmail(String email, String userId);

    UserTable updatePartialField(String userId, Map<String, Object> fields);


}
