package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="Users")
@Data //getters, setters, tostring, equals, hashcode
@NoArgsConstructor //costruttore senzza argomenti
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String bio;
    @Column(name="last_login")
    private Date lastLogin;
    @Column(name="language_preference")
    private String languagePreference;
    private String timezone;
    @Column(name="is_active")
    private String isActive;
    @Column(name="birth_date")
    private Date birthDate;
    @Column(name="created_at")
    private Date createdAt;
    private String gender;
    private float weight;
    private float height;
    private float waist;
    private String role;
    @Column(name="password_hash")
    private String passwordHash;
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    private String street;
    @Column(name="street_no")
    private String streetNo;
    @Column(name="zip_code")
    private String zipCode;
    private String country;
    private String city;
    private String province;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="updated_at")
    private Date updatedAt;


}
