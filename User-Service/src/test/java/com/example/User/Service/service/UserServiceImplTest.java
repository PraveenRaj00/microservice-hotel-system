package com.example.User.Service.service;

import com.example.User.Service.entities.UserTable;
import com.example.User.Service.exceptions.ResourceNotFoundException;
import com.example.User.Service.repository.UserRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    UserRepository userRepository;

    static UserTable user;
    static List<UserTable> userList= new ArrayList<>();

    @BeforeEach
    public void setup(){
        userList.add(user);
        user= UserTable.builder()
                .userId("1")
                .userName("Praveen Kumar")
                .email("praveen@gmail.com")
                .about("Back end Developer")
                .build();
    }

    @AfterAll
    public static void tearDown(){
        user=null;
        userList= null;
    }

    @Test
    public void testGetSingleUser_Found(){
        Mockito.when(userRepository.findById("1")).thenReturn(Optional.of(user));
        String expectedResult= userService.getOneUser("1").getUserName();
        Assertions.assertEquals(expectedResult,user.getUserName());
    }

    @Test
    public void SaveUserDetails_Test(){
        Mockito.when(userRepository.save(user)).thenReturn(user);
        String resultUser= "Praveen Kumar";
        Assertions.assertEquals(resultUser,user.getUserName());
    }

    @Test
    public void getAllUser_Test(){
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<UserTable> result= userRepository.findAll();
        Assertions.assertEquals(1,result.size());
    }

    @Test
    public void updateUserEmail_Test(){
        Mockito.when(userRepository.findById("1")).thenReturn(Optional.of(user));
        user.setEmail("abc@gmail.com");
        String newEmail= "abc@gmail.com";
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Assertions.assertEquals(newEmail,user.getEmail());

    }

}
