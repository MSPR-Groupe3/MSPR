package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.*;
import fr.epsi.mspr.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        // total value of the order(money money money)
        model.addAttribute("totalValue", productInPurchaseRepo.getTotalValue(id));

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

        // get all products in DB except the products already in purchase
        List<Product> productsAll = productRepo.findAll();
        List<Product> productsAllFiltered = new ArrayList<>(productsAll);

        Purchase purchase = purchaseRepo.getOne(id);
        List<ProductInPurchase> productInPurchases = purchase.getPurchaseLines();

        for(Product p : productsAll){
            for(ProductInPurchase pip : productInPurchases){
                if(p.getId() == pip.getProduct().getId()){
                    productsAllFiltered.remove(p);
                }
            }
        }


        model.addAttribute("productsAllFiltered", productsAllFiltered);

        return "commandes_product_add";
    }

    @PostMapping("/detailsCommande/{id}/ajouterProduit")
    public String addPurchaseProduct(
            @PathVariable("id") long id,
            @ModelAttribute("purchaseLine") ProductInPurchase purchaseLine,
            BindingResult result,
            Model model){

        if (result.hasErrors()) {
            return "redirect:/detailsCommande/{id}/ajouterProduit";
        }

        // Insert a row in product_in_purchase table
        Purchase purchase = purchaseRepo.getOne(id);
        purchaseLine.setPurchase(purchase);
        productInPurchaseRepo.save(purchaseLine);

        // Update the value of quantity in stock
        Product product = productRepo.getOne(purchaseLine.getProduct().getId());
        int newQuantityAvailable = product.getQuantityAvailable() - purchaseLine.getQuantity();
        product.setQuantityAvailable(newQuantityAvailable);
        productRepo.save(product);

        return "redirect:/detailsCommande/" + id;
    }


    //DELETE product by id in a purchase
    @PostMapping("/detailsCommande/{id}/supprimerProduit/{idProd}")
    public String removeProductFromPurchase(
            @PathVariable("id") long id,
            @PathVariable("idProd") long idProd,
            Model model){

        // delete the product in purchase
        ProductInPurchaseKey key = new ProductInPurchaseKey(idProd, id);
        int quantityAdded = productInPurchaseRepo.getOne(key).getQuantity();
        System.out.println(" ---------- " + quantityAdded);
        productInPurchaseRepo.deleteById(key);

        // update the quantity of the stock in product table
        Product product = productRepo.getOne(idProd);
        int newQuantityAvailable = product.getQuantityAvailable() + quantityAdded;
        product.setQuantityAvailable(newQuantityAvailable);
        productRepo.save(product);

        return "redirect:/detailsCommande/" + id;
    }


    // EDITER PRODUIT DANS LA COMMANDE
    @GetMapping("/detailsCommande/{id}/editerProduit/{idProd}")
    public String editProductFromPurchase(
            @PathVariable("id") long id,
            @PathVariable("idProd") long idProd,
            Model model
    ){

        // get purchaseLine pour binding
        ProductInPurchase purchaseLine = productInPurchaseRepo.getOne(new ProductInPurchaseKey(idProd, id));
        model.addAttribute("purchaseLine", purchaseLine);

        // add id (as commande id) and idProd as (product id) as model attribute
        model.addAttribute("idPurchase", id);
        model.addAttribute("idProduct", idProd);

        return "commandes_product_edit";
    }

    @PostMapping("/detailsCommande/{id}/editerProduit/{idProd}")
    public String editProductFromPurchase(
            @PathVariable("id") long id,
            @PathVariable("idProd") long idProd,
            @ModelAttribute("purchaseLine") ProductInPurchase purchaseLine,
            BindingResult result,
            Model model){


        // get purchaseLine pour binding
        ProductInPurchase purchaseLineToUpdate = productInPurchaseRepo.getOne(new ProductInPurchaseKey(idProd, id));
        int oldQuantity = purchaseLineToUpdate.getQuantity();
        int newQuantity = purchaseLine.getQuantity();
        purchaseLineToUpdate.setQuantity(purchaseLine.getQuantity());
        productInPurchaseRepo.save(purchaseLineToUpdate);

        // update the quantity of the stock in product table
        Product product = productRepo.getOne(idProd);
        int newQuantityAvailable = product.getQuantityAvailable() + oldQuantity - newQuantity;
        product.setQuantityAvailable(newQuantityAvailable);
        productRepo.save(product);



        return "redirect:/detailsCommande/" + id;
    }

}
