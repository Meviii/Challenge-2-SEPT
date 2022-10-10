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

}
