package com.fmc.accountservice;

import com.fmc.accountservice.Model.Account.Account;
import com.fmc.accountservice.Model.Account.AccountType;
import com.fmc.accountservice.Model.User.User;
import com.fmc.accountservice.Repository.AccountRepository;
import com.fmc.accountservice.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class AccountserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountserviceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner mappingDemo(UserRepository userRepository,
//										 AccountRepository accountRepository) {
//		return args -> {
//
//			// create a new book
//			User userToTest = new User("testEmailTwo@", "testName", "10/10/2000", "Engineer", "0421234312");
//
//
//			// save the book
//			userRepository.save(userToTest);
//
//			// create and save new pages
//			accountRepository.save(new Account(AccountType.LOAN, "1231231", "A loan account", "5123132", userToTest));
//			accountRepository.save(new Account(AccountType.SAVING, "123122231", "A savings account", "222123", userToTest));
//		};
//	}

}
