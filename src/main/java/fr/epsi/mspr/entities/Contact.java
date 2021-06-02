package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "contact")
    private Set<Purchase> purchases;

}
