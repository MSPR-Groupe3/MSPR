package fr.epsi.mspr.entities;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor @RequiredArgsConstructor
@Getter @Setter
@Entity
@Table
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", unique = true)
    private String reference;
    @Column(name = "date_of_order", nullable = false)
    private LocalDateTime dateOfOrder;
    @Column(name = "comment")
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
