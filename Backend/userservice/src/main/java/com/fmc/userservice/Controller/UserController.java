package com.fmc.userservice.Controller;

import com.fmc.userservice.Model.User.User;
import com.fmc.userservice.Model.User.UserList;
import com.fmc.userservice.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users currently.");
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist.");
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't update user.");
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create user. ");
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't delete user.");
        }

        return new ResponseEntity<>("User deleted.", HttpStatus.OK);
    }

    /**
     * Deletes all users
     */
    @DeleteMapping()
    public ResponseEntity<String> deleteUsers() {
        Boolean isDeleted = userService.deleteAll();

        if (!isDeleted) {
            LOGGER.warn("Couldn't delete users.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't delete users.");
        }

        return new ResponseEntity<>("All Users deleted.", HttpStatus.OK);
    }
}
