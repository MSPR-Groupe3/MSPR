package fr.epsi.mspr.entities;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class ProductInPurchaseKey implements Serializable {

    private Long productId;
    private Long purchaseId;

}
