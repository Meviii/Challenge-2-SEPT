package com.fmc.userservice.Controller;

import com.fmc.userservice.Model.Address;
import com.fmc.userservice.Model.User.User;
import com.fmc.userservice.Model.User.UserList;
import com.fmc.userservice.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userController = Mockito.mock(UserController.class);

        User testUser = new User("testEmail@", "testName", "10/10/2000", "Engineer", "042123412");
        Address address = new Address("1", "a street name", "a city", "a state", "2144", "Australia");
        User testUserTwo = new User("testEmailTwo@", "testName", "10/10/2000", "Engineer", "0421234312", address);

        userRepository.save(testUser);
        userRepository.save(testUserTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetAllUsers() {
        List<User> users = userRepository.findAll();
        UserList userList = new UserList(users);
        when(userController.getAllUsers()).thenReturn(userList);
        assertEquals(userList, userController.getAllUsers());
    }

    @Test
    void shouldGetUserById() {
        User a = userRepository.findById(1);
        when(userController.getUserById(1)).thenReturn(a);

        assertEquals(a, userController.getUserById(1));
    }

    @Test
    void shouldDeleteUserById() {
        ResponseEntity<String> response = new ResponseEntity<>("User deleted.", HttpStatus.OK);

        when(userController.deleteUser(1)).thenReturn(response);

        assertEquals(response, userController.deleteUser(1));
    }
}