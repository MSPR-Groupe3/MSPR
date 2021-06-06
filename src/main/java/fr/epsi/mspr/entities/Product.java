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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "reference", unique = true)
    private String reference;
    @Column(name = "description")
    private String description;
    @Column(name = "unit_price_before_tax")
    private float unitPriceBeforeTax;
    @Column(name = "tax_rate")
    private float taxRate;
    @Column(name = "quantity_available")
    private int quantityAvailable;
    @Column(name = "is_sellable", nullable = false)
    private boolean isSellable;

    @ManyToOne
    @JoinColumn(name="category_id", foreignKey = @ForeignKey(name = "fk_category"))
    @JsonManagedReference
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private Set<ProductInPurchase> purchaseLines;

}