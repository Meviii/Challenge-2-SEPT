package com.fmc.accountservice.Repository;

import com.fmc.accountservice.Model.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findById(long id);

//    @Query(value = "SELECT * FROM accounts WHERE user_id = ?1", nativeQuery = true)
    List<Account> findAccountsByUserId(long id);

    @Query(value = "SELECT * FROM accounts", nativeQuery = true)
    List<Account> findAll();
}
