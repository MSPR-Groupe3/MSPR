package fr.epsi.mspr.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data @AllArgsConstructor @RequiredArgsConstructor
public class Product {

    private int id;
    private String name;
    private String reference;
    private String description;
    private float unitPriceBeforeTax;
    private float taxRate;
    private int quantityAvailable;
    private boolean isSellable;

}
