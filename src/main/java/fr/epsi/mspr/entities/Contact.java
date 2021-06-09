package fr.epsi.mspr.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Organization organization;

    @OneToMany(mappedBy = "contact")
    @JsonBackReference
    private Set<Purchase> purchases;

    // Methode pour recuperer le nom+prenom + organisation d'un contazvt
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

}