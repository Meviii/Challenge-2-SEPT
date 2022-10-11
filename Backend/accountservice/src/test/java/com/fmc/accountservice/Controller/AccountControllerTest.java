package com.fmc.accountservice.Controller;

import com.fmc.accountservice.Model.Account.Account;
import com.fmc.accountservice.Model.Account.AccountList;
import com.fmc.accountservice.Model.Account.AccountType;
import com.fmc.accountservice.Model.User.User;
import com.fmc.accountservice.Model.User.UserList;
import com.fmc.accountservice.Repository.AccountRepository;
import com.fmc.accountservice.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @Mock
    AccountRepository accountRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        accountController = Mockito.mock(AccountController.class);

        User testUser = new User("testEmail@", "testName", "10/10/2000", "Engineer", "042123412");
        userRepository.save(testUser);

        Account testAccount = new Account(AccountType.SAVING, "1231", "A savings account", "50000", testUser);
        accountRepository.save(testAccount);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        AccountList accountList = new AccountList(accounts);
        when(accountController.getAllAccounts()).thenReturn(accountList);
        assertEquals(accountList, accountController.getAllAccounts());
    }

    @Test
    void shouldGetAccountById() {
        Account accToFind = accountRepository.findById(1);
        when(accountController.getAccountById(1)).thenReturn(accToFind);

        assertEquals(accToFind, accountController.getAccountById(1));
    }

    @Test
    void shouldGetAccountsForUserById() {
        List<Account> userAccs = accountRepository.findAccountsByUserId(1);
        AccountList userAccsList = new AccountList(userAccs);

        when(accountController.getAccountsForUserById(1)).thenReturn(userAccsList);

        assertEquals(userAccsList, accountController.getAccountsForUserById(1));
    }
}