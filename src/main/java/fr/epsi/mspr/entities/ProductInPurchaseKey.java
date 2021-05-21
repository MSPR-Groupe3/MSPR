package fr.epsi.mspr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class ProductInPurchaseKey implements Serializable {

    private int productId;
    private int purchaseId;

}
