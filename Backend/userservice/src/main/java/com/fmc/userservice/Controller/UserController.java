package com.fmc.userservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fmc.userservice.Exception.CustomException;
import com.fmc.userservice.Model.User;
import com.fmc.userservice.Model.UserList;
import com.fmc.userservice.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * WEB Endpoint controller/ URI Controller
 */
@RestController
@RequestMapping(path="/api/v1/users")
public class UserController {

    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Main constructor for the user controller with user service
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    /**
     * Returns the details of all users
     */
    @GetMapping(produces = "application/json")
    public UserList getAllUsers() {
        UserList toReturn = userService.getAllUsers();

        if (toReturn.isEmpty()) {
            LOGGER.info("No users currently");
            throw new CustomException("No users currently.", HttpStatus.NOT_FOUND);
        }
        return toReturn;
    }

    /**
     * Returns the user details by the unique user id
     *
     * @param id - user id
     */
    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseBody
    public User getUserById(@PathVariable long id) {
        User toReturn = userService.getUserById(id);

        if (toReturn == null) {
            LOGGER.info("Couldn't find user with ID: " + id);
            throw new CustomException("User doesn't not exist", HttpStatus.NOT_FOUND);
        }

        return toReturn;
    }

    /**
     * Updates the user by the given payload and id
     *
     * @param id         - user id
     */
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable long id) {

        Boolean isUpdated = userService.updateUser(user, id);

        if (!isUpdated) {
            LOGGER.warn("Couldn't update user with ID: " + id);
            throw new CustomException("Couldn't update user", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User updated.", HttpStatus.OK);
    }

    /**
     * Creates a user by a given valid payload
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        Boolean isCreated = userService.createUser(user);

        if (!isCreated) {
            LOGGER.warn("Couldn't create user.");
            throw new CustomException("Couldn't create user", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User created.", HttpStatus.CREATED);
    }

    /**
     * Deletes a user by the given id
     *
     * @param id - user id
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {

        Boolean isDeleted = userService.deleteUser(id);

        if (!isDeleted) {
            LOGGER.warn("Couldn't delete user with ID: " + id);
            throw new CustomException("Couldn't delete user", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User deleted.", HttpStatus.OK);
    }

    /**
     * Deletes all users
     */
    @DeleteMapping(path = "/")
    public ResponseEntity<String> deleteUser() {
        Boolean isDeleted = userService.deleteAll();

        if (!isDeleted) {
            LOGGER.warn("Couldn't delete users.");
            throw new CustomException("Couldn't delete users", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("All Users deleted.", HttpStatus.OK);
    }
}
