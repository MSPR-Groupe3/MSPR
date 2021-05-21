package fr.epsi.mspr.entities;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data @AllArgsConstructor @RequiredArgsConstructor
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reference;
    private LocalDateTime dateOfOrder;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @OneToMany(mappedBy = "purchase")
    private Set<ProductInPurchase> purchaseLines;

}
