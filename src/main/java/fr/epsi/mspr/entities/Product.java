package fr.epsi.mspr.entities;

import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@AllArgsConstructor @RequiredArgsConstructor
@Getter @Setter
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Pattern(regexp = "[A-Za-zçéèàêîô',.?!& -]+")
    private String name;
    @Column(name = "reference", unique = true)
    private String reference;
    @Column(name = "description")
    private String description;
    @Column(name = "unit_price_before_tax")
    private float unitPriceBeforeTax;
    @Column(name = "tax_rate")
    private float taxRate = 0.2f;
    @Column(name = "quantity_available")
    private int quantityAvailable;
    @Column(name = "is_sellable", nullable = false)
    private boolean isSellable = true;

    @ManyToOne
    @JoinColumn(name="category_id", foreignKey = @ForeignKey(name = "fk_category"))
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<ProductInPurchase> purchaseLines;

}
