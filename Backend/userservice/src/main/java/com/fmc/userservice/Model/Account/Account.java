package com.fmc.userservice.Model.Account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fmc.userservice.Model.User.User;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private AccountType acc_type;
    private String acc_number;
    private String acc_name;
    private String balance;
    private String curr_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Account(AccountType acc_type, String acc_number, String acc_name, String balance, String curr_date, User user) {
        this.acc_type = acc_type;
        this.acc_number = acc_number;
        this.acc_name = acc_name;
        this.balance = balance;
        this.curr_date = curr_date;
        this.user = user;
    }

    public Account(AccountType acc_type, String acc_number, String acc_name, String balance, User user) {
        this.acc_type = acc_type;
        this.acc_number = acc_number;
        this.acc_name = acc_name;
        this.balance = balance;
        this.user = user;
        this.curr_date = Calendar.getInstance().getTime().toString();
    }

}
