package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.Contact;
import fr.epsi.mspr.entities.Product;
import fr.epsi.mspr.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    // SHOW ALL PRODUCTS
    @GetMapping("/listerProduits")
    public String showProductList(Model model){
        model.addAttribute("products", productRepo.findAll());
        return "produits";
    }

    // SHOW PRODUCTS BY CATEGORY
    /*@GetMapping("/CategoryProducts")
    public String showCategoryProductsList(Model model, int category_id){
        model.addAttribute("productsByCategory", productRepo.findByCategoryId(category_id));
        return "produits";
    }*/

    @GetMapping("/creerProduit")
    public String createProduct(Model model) {

        Product product = this.productRepo.findById(1L).get();
        model.addAttribute("product", product);
        return "produits_info";
    }

    // CREATE
    @PostMapping("/sauverProduit")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/creerProduit";
        }

        productRepo.save(product);
        return "redirect:/listerProduit";
    }

    // UPDATE
    @GetMapping("/modifierProduit")
    public String updateProduct(@PathVariable("id") long id, Model model) {
        Product product= productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);
        return "produits_info";
    }

    // DELETE
    @GetMapping("/supprimerProduit")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        productRepo.delete(product);
        return "produits";
    }

}
