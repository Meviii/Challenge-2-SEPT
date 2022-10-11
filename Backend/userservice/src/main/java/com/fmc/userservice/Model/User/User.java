package com.fmc.userservice.Model.User;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fmc.userservice.Model.Account.Account;
import com.fmc.userservice.Model.Address;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;

    public User(String email, String name, String birth_date, String job, String phone_no) {
        this.email = email;
        this.name = name;
        this.birth_date = birth_date;
        this.job = job;
        this.phone_no = phone_no;
    }

    public User(String email, String name, String birth_date, String job, String phone_no, Address address) {
        this.email = email;
        this.name = name;
        this.birth_date = birth_date;
        this.job = job;
        this.phone_no = phone_no;
        this.address = address;
    }
}
