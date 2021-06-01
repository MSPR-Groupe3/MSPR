package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.Purchase;
import fr.epsi.mspr.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepo;

    // CREATE
    @PostMapping("/addpurchase")
    public String addPurchase(@Valid Purchase purchase, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "mycommandes";
        }

        purchaseRepo.save(purchase);
        return "mycommandes";
    }

    // READ ALL
    @GetMapping("/purchases")
    public String showPurchaseList(Model model) {
        model.addAttribute("purchases", purchaseRepo.findAll());
        return "mycommandes";
    }

    // READ ONE
    @GetMapping("/purchase{id}")
    public String showPurchase(@PathVariable("id") long id, Model model) {
        Purchase purchase = purchaseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid purchase Id:" + id));
        purchaseRepo.delete(purchase);
        return "mycommandes";
    }
}
