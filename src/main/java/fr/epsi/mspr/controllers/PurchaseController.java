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
import java.util.Optional;

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


    // Sauver Commande existante puis rediriger vers la liste des commandes
    @PostMapping("/sauverCommande/{id}")
    public String savePurchase(@ModelAttribute("purchase") Purchase purchase, @PathVariable Long id, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/creerCommande";
        }
        // Save purchase
        Purchase purchaseToUpdate = purchaseRepo.getOne(id);
        purchaseToUpdate.setReference(purchase.getReference());
        purchaseToUpdate.setDateOfOrder(purchase.getDateOfOrder());
        purchaseToUpdate.setContact(purchase.getContact());
        purchaseRepo.save(purchaseToUpdate);

        return "redirect:/listerCommandes";
    }


    @GetMapping("/creerCommande")
    public String createPurchase(Model model) {

        // Create an Purchase instance to bind data
        Purchase purchase = new Purchase();
        model.addAttribute("purchase", purchase);

        // get all Contact
        List<Contact> contactsAll = contactRepo.findAll();
        model.addAttribute("contactsAll", contactsAll);

        return "commandes_create";
    }
    @PostMapping("/creerCommande")
    public String createPurchase(@ModelAttribute("purchase") Purchase purchase, BindingResult result, Model model){
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

    // Add product to a commande
    @GetMapping("/detailsCommande/{id}/ajouterProduit")
    public String addPurchaseProduct(@PathVariable("id") long id, Model model){

        // Create ProductInPurchase pour binding
        ProductInPurchase purchaseLine = new ProductInPurchase();
        model.addAttribute("purchaseLine", purchaseLine);

        // add id (as commande id) as model attribute
        model.addAttribute("idPurchase", id);

        // get all products in DB
        model.addAttribute("productsAll", productRepo.findAll());

        return "commandes_product";
    }

    @PostMapping("/detailsCommande/{id}/ajouterProduit")
    public String addPurchaseProduct(
            @PathVariable("id") long id,
            @ModelAttribute("purchaseLine") ProductInPurchase purchaseLine,
            BindingResult result,
            Model model){

        Purchase purchase = purchaseRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid purchase Id:" + id));
        purchaseLine.setPurchase(purchase);

        productInPurchaseRepo.saveAndFlush(purchaseLine);

        return "redirect:/detailsCommande/" + id;
    }


}
