package com.fmc.userservice.Service;

import com.fmc.userservice.Exception.CustomException;
import com.fmc.userservice.Model.User.User;
import com.fmc.userservice.Model.User.UserList;
import com.fmc.userservice.Repository.AccountRepository;
import com.fmc.userservice.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Business logic of User URI endpoints and data handling.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * Main constructor for the User service.
     *
     * @param userRepository    - Initializes global user repository
     * @param accountRepository
     */
    @Autowired
    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        LOGGER.info("User service started.");
    }

    /**
     * Finds and returns a UserList of all users found in the datastore.
     *
     * @return - UserList
     */
    public UserList getAllUsers() {
        LOGGER.info("Getting all users.");
        return new UserList(userRepository.findAll());
    }

    /**
     * Finds and returns a User by id if it is found in the datastore.
     *
     * @param id - User id
     * @return - User OR null
     */
    public User getUserById(long id) {
        LOGGER.info("Getting user with ID: " + id);
        return userRepository.findById(id);

    }

    /**
     * Finds and updates a User by given User object and id if id is found in the datastore.
     *
     * @param user - User object
     * @param id   - User id
     * @return - Boolean value representing if user has been updated
     */
    public Boolean updateUser(User user, long id) {
        boolean isUpdated = true;
        LOGGER.info("Updating user with ID: " + id);
        try {
            user.setId(id);
            userRepository.save(user);
        } catch (Exception e) {
            isUpdated = false;
        }
        return isUpdated;

    }

    /**
     * Creates a new User and saves into datastore.
     *
     * @param user - User object to save
     * @return - Boolean value representing if user has been created
     */
    public Boolean createUser(User user) {
        boolean isCreated = true;
        LOGGER.info("Creating user.");
        try {
            userRepository.save(user);

        } catch (Exception e) {
            throw new CustomException("Couldn't create user", HttpStatus.BAD_REQUEST, e);
        }
        return isCreated;
    }

    /**
     * Deletes a User if id exists in datastore
     *
     * @param id - User id to delete
     * @return - Boolean value representing if user has been deleted
     */
    public Boolean deleteUser(long id) {
        LOGGER.info("Deleting user with ID: " + id);
        boolean isDeleted = true;
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            isDeleted = false;
        }
        return isDeleted;
    }

    /**
     * Deletes all Users which exist in the datastore
     *
     * @return - Boolean value representing if users have been deleted
     */
    public Boolean deleteAll() {
        LOGGER.info("Deleting all users.");
        boolean isDeleted = true;
        try {
            accountRepository.deleteAll();
            userRepository.deleteAll();
        } catch (Exception e) {
            isDeleted = false;
        }
        return isDeleted;
    }
}