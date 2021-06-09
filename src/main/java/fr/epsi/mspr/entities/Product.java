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
    @Pattern(regexp = "[A-Z]{3}-[A-Z]{3}-[0-9]{3}")
    private String reference;
    @Column(name = "description")
    @Pattern(regexp = "[A-Za-zçéèàêîô',.?!& -]+")
    private String description;
    @Column(name = "unit_price_before_tax")
    //@Pattern(regexp = "[0-9]{1,9}.[0-9]{0,2}")
    private float unitPriceBeforeTax;
    @Column(name = "tax_rate")
    //@Pattern(regexp = "[0-9].[0-9]{0,2}")
    private float taxRate = 0.2f;
    @Column(name = "quantity_available")
    //@Pattern(regexp = "[0-9]{1,9}")
    private int quantityAvailable;
    @Column(name = "is_sellable", nullable = false)
    private boolean isSellable = true;

    @ManyToOne
    @JoinColumn(name="category_id", foreignKey = @ForeignKey(name = "fk_category"))
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<ProductInPurchase> purchaseLines;

}
