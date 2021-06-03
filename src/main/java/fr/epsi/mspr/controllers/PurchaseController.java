package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.Organization;
import fr.epsi.mspr.entities.Product;
import fr.epsi.mspr.entities.Purchase;
import fr.epsi.mspr.repositories.OrganizationRepository;
import fr.epsi.mspr.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepo;

    @Autowired
    private OrganizationRepository organizationRepo;


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
        // get purchases
        List<Purchase> purchases =  purchaseRepo.findAll();
        model.addAttribute("purchases", purchases);

        // get organizations
        List<Organization> organizations = organizationRepo.findAll();

        model.addAttribute("organizations", organizations);

        return "commandes";
    }

    // READ By Category
    public List<Purchase> findPurchaseByOrganization(Long id){
        List<Purchase> purchases = new ArrayList<>();
        for (Purchase purchase: purchaseRepo.findAll()){
            if (purchase.getContact().getOrganization().getId() == id){
                purchases.add(purchase);
            }
        }
        return purchases;
    }

    @RequestMapping(value="/listerCommandes/category", method = RequestMethod.GET)
    public String showPurchaseListByCategory(Model model, @RequestParam("id") Long id) {
        // get purchases
        List<Purchase> purchases =  findPurchaseByOrganization(id);
        model.addAttribute("purchases", purchases);

        // get organizations
        List<Organization> organizations = organizationRepo.findAll();
        model.addAttribute("organizations", organizations);


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
