package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data //getters, setters, tostring, equals, hashcode
@Builder
@NoArgsConstructor //costruttore senzza argomenti
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails {
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
    @Column(nullable = false)

    private RoleType role;
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

//    @ManyToMany
//    @JoinTable(name = "user_roles")
//    private Set<Role> roles = new HashSet<>();

//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
