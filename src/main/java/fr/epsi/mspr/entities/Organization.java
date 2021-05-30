package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "companyName", nullable = false)
    private String companyName;
    @Column(name = "streetName1", nullable = false)
    private String streetName1;
    @Column(name = "streetName2")
    private String streetName2;
    @Column(name = "streetName3")
    private String streetName3;
    @Column(name = "cityName", nullable = false)
    private String cityName;
    @Column(name = "postalCode", nullable = false)
    private String postalCode;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "organization")
    private Set<Contact> contacts;

}
