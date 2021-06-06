package fr.epsi.mspr.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor @RequiredArgsConstructor
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
    @JsonBackReference
    private Set<Organization> organizations;

    @OneToMany(mappedBy = "seller")
    @JsonBackReference
    private Set<Purchase> purchases;

}