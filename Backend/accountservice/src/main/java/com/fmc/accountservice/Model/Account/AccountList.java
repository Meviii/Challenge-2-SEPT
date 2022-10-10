package com.fmc.accountservice.Model.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class AccountList {
    private List<Account> accounts;

    public AccountList(){
        accounts = new ArrayList<>();
    }

    public AccountList(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @JsonIgnore
    public Boolean isEmpty(){
        return accounts.isEmpty();
    }
}
