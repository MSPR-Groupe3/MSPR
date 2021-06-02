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
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "street_address1", nullable = false)
    private String streetName1;
    @Column(name = "street_address2")
    private String streetName2;
    @Column(name = "street_address3")
    private String streetName3;
    @Column(name = "city_name", nullable = false)
    private String cityName;
    @Column(name = "postal_code", nullable = false)
    private String postalCode;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "organization")
    private Set<Contact> contacts;

}
