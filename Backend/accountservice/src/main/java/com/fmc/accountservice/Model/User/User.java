package com.fmc.accountservice.Model.User;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fmc.accountservice.Model.Account.Account;
import com.fmc.accountservice.Model.Account.AccountList;
import com.fmc.accountservice.Model.Address;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String birth_date;
    private String job;
    private String phone_no;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Account> accounts = new ArrayList<>();

    public User(String email, String name, String birth_date, String job, String phone_no) {
        this.email = email;
        this.name = name;
        this.birth_date = birth_date;
        this.job = job;
        this.phone_no = phone_no;
    }
}
