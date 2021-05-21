package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data @AllArgsConstructor @RequiredArgsConstructor
public class ProductInOrder {

    private Product product;
    private Purchase purchase;
    private int quantity;
    private float price;

}
