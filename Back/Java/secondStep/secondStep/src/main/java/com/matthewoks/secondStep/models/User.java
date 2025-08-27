package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Data //getters, setters, tostring, equals, hashcode
@NoArgsConstructor //costruttore senzza argomenti
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true,nullable = false)
    private String username;
    private String bio;
    @Column(name="last_login")
    private Date lastLogin;
    @Column(name="language_preference")
    private String languagePreference;
    private String timezone;
    @Column(name="is_active")
    private String isActive = "Y";
    @Column(name="birth_date")
    private Date birthDate;
    @Column(name="created_at")
    private Date createdAt;
    private String gender;
    private float weight;
    private float height;
    private float waist;
    @Enumerated(EnumType.STRING)
    private String role;
    @Column(name="password_hash",nullable = false)
    private String passwordHash;
    @Column(unique = true,nullable = false)
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

    @ManyToMany
    @JoinTable(name = "user_roles")
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
