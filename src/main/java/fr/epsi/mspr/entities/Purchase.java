package fr.epsi.mspr.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", unique = true)
    private String reference;

    @Column(name = "date_of_order", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateOfOrder;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "contact_id", foreignKey = @ForeignKey(name = "fk_contact"))
    @JsonManagedReference
    private Contact contact;

    @OneToMany(mappedBy = "purchase")
    @JsonBackReference
    private List<ProductInPurchase> purchaseLines;
    // IMPORTANT : il faut utiliser List au lieu de Set afin d'utiliser
    // th:field="*{purchaseLines[__${productLineStat.index}__].quantity}", on ne peut pas itérer par index sur Set

    @ManyToOne
    @JoinColumn(name = "seller_id", foreignKey = @ForeignKey(name = "fk_seller"))
    @JsonManagedReference
    private User seller;

}