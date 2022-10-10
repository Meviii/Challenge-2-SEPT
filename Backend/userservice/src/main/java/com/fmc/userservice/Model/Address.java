package com.fmc.userservice.Model;

import lombok.*;

import javax.persistence.*;

/**
 * @Dependant This class depends on the User object.
 *
 * Each User object can hold an object of this class.
 */
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String streetNo;

    private String streetName;

    private String city;

    private String state;

    private String postCode;

    private String country;

    public Address(String streetNo, String streetName, String city, String state, String postCode, String country) {
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
        this.country = country;
    }
}