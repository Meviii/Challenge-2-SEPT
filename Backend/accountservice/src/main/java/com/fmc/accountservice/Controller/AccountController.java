package com.fmc.accountservice.Controller;

import com.fmc.accountservice.Model.Account.Account;
import com.fmc.accountservice.Model.Account.AccountList;
import com.fmc.accountservice.Service.AccountService;
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
@RequestMapping(path="/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    /**
     * Main constructor for the account controller with account service
     */
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Returns the details of all accounts
     */
    @GetMapping(produces = "application/json")
    public AccountList getAllAccounts() {
        AccountList toReturn = accountService.getAllAccounts();

        if (toReturn.isEmpty()) {
            LOGGER.info("No accounts currently");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No accounts currently.");
        }
        return toReturn;
    }

    /**
     * Returns the account details by the unique account id
     *
     * @param id - account id
     */
    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseBody
    public Account getAccountById(@PathVariable long id) {
        Account toReturn = accountService.getAccountById(id);

        if (toReturn == null) {
            LOGGER.info("Couldn't find account with ID: " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account doesn't exist.");
        }

        return toReturn;
    }

    /**
     * Returns the account details by the unique account id
     *
     * @param id - account id
     */
    @GetMapping(path = "/user/{id}", produces = "application/json")
    @ResponseBody
    public AccountList getAccountsForUserById(@PathVariable long id) {
        AccountList toReturn = accountService.getAccountsForUserById(id);

        if (toReturn.isEmpty()) {
            LOGGER.info("This user has no accounts.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user has no accounts.");
        }

        return toReturn;
    }

    /**
     * Updates the account by the given payload and id
     *
     * @param id         - account id
     */
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateAccount(@RequestBody Account account, @PathVariable long id) {

        Boolean isUpdated = accountService.updateAccount(account, id);

        if (!isUpdated) {
            LOGGER.warn("Couldn't update account with ID: " + id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't update account.");
        }

        return new ResponseEntity<>("Account updated.", HttpStatus.OK);
    }

    /**
     * Creates an account by a given valid payload
     */
    @PostMapping(path = "/user/{id}", produces = "application/json")
    public ResponseEntity<String> createAccountForUserById(@RequestBody Account account, @PathVariable long id) {
        Boolean isCreated = accountService.createAccountForUserById(account, id);

        if (!isCreated) {
            LOGGER.warn("Couldn't create account.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create account. ");
        }

        return new ResponseEntity<>("Account created.", HttpStatus.CREATED);
    }

    /**
     * Deletes a account by the given id
     *
     * @param id - account id
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id) {

        Boolean isDeleted = accountService.deleteAccount(id);

        if (!isDeleted) {
            LOGGER.warn("Couldn't delete account with ID: " + id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't delete account.");
        }

        return new ResponseEntity<>("Account deleted.", HttpStatus.OK);
    }

    /**
     * Deletes all accounts
     */
    @DeleteMapping()
    public ResponseEntity<String> deleteAccounts() {
        Boolean isDeleted = accountService.deleteAll();

        if (!isDeleted) {
            LOGGER.warn("Couldn't delete accounts.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't delete accounts.");
        }

        return new ResponseEntity<>("All Accounts deleted.", HttpStatus.OK);
    }
}
