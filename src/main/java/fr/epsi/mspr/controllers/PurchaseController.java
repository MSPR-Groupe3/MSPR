package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.*;
import fr.epsi.mspr.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepo;

    @Autowired
    private OrganizationRepository organizationRepo;

    @Autowired
    private ContactRepository contactRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductInPurchaseRepository productInPurchaseRepo;


    @GetMapping("/creerCommande")
    public String createPurchase(Model model) {
        // get all Contact
        List<Contact> contacts = contactRepo.findAll();
        model.addAttribute("contacts",contacts);

        return "commandes_create";
    }

    // CREATE
    @PostMapping("/sauverCommande/{id}")
    public String addProduct(@ModelAttribute("purchase") Purchase purchase, @PathVariable Long id, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/creerCommande";
        }

        // Save purchase
        Purchase purchaseToUpdate = purchaseRepo.getOne(id);
        purchaseToUpdate.setReference(purchase.getReference());
        purchaseToUpdate.setDateOfOrder(purchase.getDateOfOrder());
        purchaseToUpdate.setContact(purchase.getContact());
        purchaseRepo.save(purchaseToUpdate);

        // Save ProductInPurchase: we delete all the row for the purchase id, and we add new row
        for(ProductInPurchase pur : purchaseToUpdate.getPurchaseLines()){
            productInPurchaseRepo.deleteById(pur.getId());
        }

        for(ProductInPurchase prod : purchase.getPurchaseLines()){

            // il faut ajouter un input pour price
            Long purchaseId = id;
            Long productId = prod.getProduct().getId();
            int quantity = prod.getQuantity();
            float price = prod.getProduct().getUnitPriceBeforeTax();
            productInPurchaseRepo.insertProductInPurchase(purchaseId, productId, quantity, price);
        }



        return "redirect:/listerCommandes";
    }

    // READ ALL
    @GetMapping("/listerCommandes")
    public String showPurchaseList(Model model) {
        // get purchases
        model.addAttribute("purchases", purchaseRepo.findAll());

        // get organizations
        model.addAttribute("organizations", organizationRepo.findAll());

        return "commandes";
    }


    // READ By Category
    @RequestMapping(value="/listerCommandes/organisation", method = RequestMethod.GET)
    public String showPurchaseListByCategory(Model model, @RequestParam("id") Long id) {
        // get purchases
        List<Purchase> purchases;
        if (id == 0){
            purchases = purchaseRepo.findAll();
        } else {
            purchases = purchaseRepo.findPurchaseByOrganization(id);
        }
        model.addAttribute("purchases", purchases);
        model.addAttribute("organisation_selected", id);

        // get organizations
        List<Organization> organizations = organizationRepo.findAll();
        model.addAttribute("organizations", organizations);


        return "commandes";
    }





    // READ ONE
    @GetMapping("/detailsCommande/{id}")
    public String showPurchase(@PathVariable("id") long id, Model model) {
        Purchase purchase = purchaseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid purchase Id:" + id));
        model.addAttribute("purchase", purchase);

        // all products of DB
        model.addAttribute("productsAll", productRepo.findAll());

        // all contacts
        model.addAttribute("contactsAll", contactRepo.findAll());

        return "commandes_info.html";
    }
}
