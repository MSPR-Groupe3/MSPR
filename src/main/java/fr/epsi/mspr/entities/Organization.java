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

    @Column(name = "COMPANYNAME", nullable = false)
    private String companyName;
    @Column(name = "STREETNAME1", nullable = false)
    private String streetName1;
    @Column(name = "STREETNAME2")
    private String streetName2;
    @Column(name = "STREETNAME3")
    private String streetName3;
    @Column(name = "CITYNAME", nullable = false)
    private String cityName;
    @Column(name = "POSTALCODE", nullable = false)
    private String postalCode;
    @Column(name = "COUNTRY", nullable = false)
    private String country;
    @Column(name = "PHONENUMBER")
    private String phoneNumber;
    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "organization")
    private Set<Contact> contacts;

}
