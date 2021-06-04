package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.ProductInPurchase;
import fr.epsi.mspr.entities.ProductInPurchaseKey;
import fr.epsi.mspr.entities.Purchase;
import fr.epsi.mspr.repositories.ProductInPurchaseRepository;
import fr.epsi.mspr.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatController {

    @Autowired
    private ProductInPurchaseRepository productInPurchaseRepo;
    @Autowired
    private PurchaseRepository purchaseRepo;

    @GetMapping("/statistiques")
    public String showStatistics(Model model){
        ProductInPurchase biggestProductInPurchase = productInPurchaseRepo.findByOrderByPriceDesc().get(0);
        Long biggestPurchaseId = biggestProductInPurchase.getId().getPurchaseId();
        Purchase biggestPurchase = purchaseRepo.findById(biggestPurchaseId).get();
        model.addAttribute("biggestProductInPurchase", biggestProductInPurchase);
        model.addAttribute("biggestPurchase", biggestPurchase);
        return "stats";
    }
}
