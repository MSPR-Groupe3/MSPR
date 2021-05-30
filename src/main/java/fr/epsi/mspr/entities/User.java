package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "loginEmail", nullable = false)
    private String loginEmail;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Organization> organizations;

    @OneToMany(mappedBy = "seller")
    private Set<Purchase> purchases;

}