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
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "REFERENCE", unique = true)
    private String reference;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "UNITPRICEBEFORETAX")
    private float unitPriceBeforeTax;
    @Column(name = "TAXRATE")
    private float taxRate;
    @Column(name = "QUANTITYAVAILABLE")
    private int quantityAvailable;
    @Column(name = "ISSELLABLE", nullable = false)
    private boolean isSellable;

    @ManyToOne
    @JoinColumn(name="category_id", foreignKey = @ForeignKey(name = "fk_category"))
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<ProductInPurchase> purchaseLines;

}
