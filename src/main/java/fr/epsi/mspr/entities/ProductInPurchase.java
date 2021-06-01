package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table
public class ProductInPurchase {

    @EmbeddedId
    ProductInPurchaseKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product"))
    private Product product;

    @ManyToOne
    @MapsId("purchaseId")
    @JoinColumn(name = "purchase_id", foreignKey = @ForeignKey(name = "fk_purchase"))
    private Purchase purchase;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;
    @Column(name = "PRICE", nullable = false)
    private float price;
}