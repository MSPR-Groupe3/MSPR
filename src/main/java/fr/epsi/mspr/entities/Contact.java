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
    private int id;

    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "PHONENUMBER")
    private String phoneNumber;
    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "organization_id", foreignKey=@ForeignKey(name = "fk_organization"))
    private Organization organization;

    @OneToMany(mappedBy = "contact")
    private Set<Purchase> purchases;

}
