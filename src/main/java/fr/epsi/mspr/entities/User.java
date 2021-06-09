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
    private int id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "LOGINEMAIL", nullable = false)
    private String loginEmail;

    @Column(name = "PASSWD", nullable = false)
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "role_in_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Organization> organizations;

    @OneToMany(mappedBy = "seller")
    @JsonBackReference
    private Set<Purchase> purchases;

    public String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }

}