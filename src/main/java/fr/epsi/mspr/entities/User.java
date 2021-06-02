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
    private Long id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "loginemail", nullable = false)
    private String loginEmail;
    @Column(name = "passwd", nullable = false)
    private String password;
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Organization> organizations;

    @OneToMany(mappedBy = "seller")
    private Set<Purchase> purchases;

}