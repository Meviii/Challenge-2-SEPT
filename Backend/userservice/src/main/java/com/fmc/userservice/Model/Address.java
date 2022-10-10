package com.fmc.userservice.Model;

import lombok.*;

import javax.persistence.*;

/**
 * @Dependant This class depends on the User object.
 *
 * Each User object can hold an object of this class.
 */
@Entity
@Table(name="addresses")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer streetNo;

    private String streetName;

    private String city;

    private String state;

    private Integer postCode;

    private String country;

}