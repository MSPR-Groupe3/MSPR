package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data @AllArgsConstructor @RequiredArgsConstructor
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
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<ProductInPurchase> purchaseLines;

}
