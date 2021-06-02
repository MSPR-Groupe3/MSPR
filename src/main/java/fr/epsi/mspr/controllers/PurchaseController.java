package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.Product;
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

    @GetMapping("/creerCommande")
    public String createPurchase(Model model) {

        Purchase purchase= this.purchaseRepo.findById(1L).get();
        model.addAttribute("purchase", purchase);
        return "commandes_info";
    }

    // CREATE
    @PostMapping("/sauverCommande")
    public String addProduct(@Valid Purchase purchase, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/creerCommande";
        }

        purchaseRepo.save(purchase);
        return "redirect:/listerCommandes";
    }

    // READ ALL
    @GetMapping("/listerCommandes")
    public String showPurchaseList(Model model) {
        model.addAttribute("purchases", purchaseRepo.findAll());
        return "commandes";
    }

    // READ ONE
    @GetMapping("/detailsCommande")
    public String showPurchase(@PathVariable("id") long id, Model model) {
        Purchase purchase = purchaseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid purchase Id:" + id));
        purchaseRepo.delete(purchase);
        return "commandes_info";
    }
}
