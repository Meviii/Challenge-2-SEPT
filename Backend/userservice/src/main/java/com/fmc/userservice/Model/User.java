package com.fmc.userservice.Model;


import lombok.*;

import javax.persistence.*;

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
