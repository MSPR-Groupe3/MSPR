package fr.epsi.mspr.entities;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "REFERENCE", unique = true)
    private String reference;
    @Column(name = "DATEOFORDER", nullable = false)
    private LocalDateTime dateOfOrder;
    @Column(name = "COMMENT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "contact_id", foreignKey = @ForeignKey(name = "fk_contact"))
    private Contact contact;

    @OneToMany(mappedBy = "purchase")
    private Set<ProductInPurchase> purchaseLines;

    @ManyToOne
    @JoinColumn(name = "seller_id", foreignKey = @ForeignKey(name = "fk_seller"))
    private User seller;

}
