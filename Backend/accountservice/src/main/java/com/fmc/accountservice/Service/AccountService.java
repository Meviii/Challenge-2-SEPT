package com.fmc.accountservice.Service;

import com.fmc.accountservice.Model.Account.Account;
import com.fmc.accountservice.Model.Account.AccountList;
import com.fmc.accountservice.Model.User.User;
import com.fmc.accountservice.Repository.AccountRepository;
import com.fmc.accountservice.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic of Account URI endpoints and data handling.
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    /**
     * Main constructor for the Account service.
     *
     * @param accountRepository  - Initializes global account repository
     */
    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        LOGGER.info("Account service started.");
    }

    /**
     * Finds and returns a AccountList of all accounts found in the datastore.
     *
     * @return - AccountList
     */
    public AccountList getAllAccounts() {
        LOGGER.info("Getting all accounts.");
        return new AccountList(accountRepository.findAll());
    }

    /**
     * Finds and returns an Account by id if it is found in the datastore.
     *
     * @param id - Account id
     * @return - Account OR null
     */
    public Account getAccountById(long id) {
        LOGGER.info("Getting account with ID: " + id);
        return accountRepository.findById(id);

    }

    /**
     * Finds and updates an Account by given Account object and id if id is found in the datastore.
     *
     * @param account - Account object
     * @param id   - Account id
     * @return - Boolean value representing if account has been updated
     */
    public Boolean updateAccount(Account account, long id) {
        boolean isUpdated = true;
        LOGGER.info("Updating account with ID: " + id);
        try {
            account.setId(id);
            accountRepository.save(account);
        } catch (Exception e) {
            isUpdated = false;
        }
        return isUpdated;

    }

    /**
     * Creates a new account for a user and saves into datastore.
     *
     * @param account - Account object to save
     * @return - Boolean value representing if account has been created
     */
    public Boolean createAccountForUserById(Account account, long id) {
        boolean isCreated = true;
        LOGGER.info("Creating account for user id: " + id);
        try {
            User userToMap = userRepository.findById(id);
            if (userToMap != null) {
                account.setUser(userToMap);
                accountRepository.save(account);
            }else{
                isCreated = false;
            }
        } catch (Exception e) {
            isCreated = false;
        }
        return isCreated;
    }

    /**
     * Deletes an Account if id exists in datastore
     *
     * @param id - Account id to delete
     * @return - Boolean value representing if account has been deleted
     */
    public Boolean deleteAccount(long id) {
        LOGGER.info("Deleting account with ID: " + id);
        boolean isDeleted = true;
        try {
            accountRepository.deleteById(id);
        } catch (Exception e) {
            isDeleted = false;
        }
        return isDeleted;
    }

    /**
     * Deletes all Accounts which exist in the datastore
     *
     * @return - Boolean value representing if accounts have been deleted
     */
    public Boolean deleteAll() {
        LOGGER.info("Deleting all accounts.");
        boolean isDeleted = true;
        try {
            accountRepository.deleteAll();
        } catch (Exception e) {
            isDeleted = false;
        }
        return isDeleted;
    }

    /**
     * Finds and returns an Account by a given user id if it is found in the datastore.
     *
     * @param id - User id
     * @return - AccountList
     */
    public AccountList getAccountsForUserById(long id) {
        LOGGER.info("Getting all accounts for user: " + id);
        return new AccountList(accountRepository.findAccountsByUserId(id));
    }
}