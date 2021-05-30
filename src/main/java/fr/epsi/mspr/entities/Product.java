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

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "reference", unique = true)
    private String reference;
    @Column(name = "description")
    private String description;
    @Column(name = "unitPriceBeforeTax")
    private float unitPriceBeforeTax;
    @Column(name = "taxRate")
    private float taxRate;
    @Column(name = "quantityAvailable")
    private int quantityAvailable;
    @Column(name = "isSellable", nullable = false)
    private boolean isSellable;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<ProductInPurchase> purchaseLines;

}
