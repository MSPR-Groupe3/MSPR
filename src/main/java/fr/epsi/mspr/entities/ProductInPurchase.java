package fr.epsi.mspr.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table
public class ProductInPurchase {

    @EmbeddedId
    ProductInPurchaseKey id = new ProductInPurchaseKey();

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product"))
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @MapsId("purchaseId")
    @JoinColumn(name = "purchase_id", foreignKey = @ForeignKey(name = "fk_purchase"))
    @JsonManagedReference
    private Purchase purchase;

    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "price", nullable = false)
    private float price;

}