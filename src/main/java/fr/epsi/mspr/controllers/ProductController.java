package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.*;
import fr.epsi.mspr.repositories.CategoryRepository;
import fr.epsi.mspr.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    // SHOW ALL PRODUCTS
    @GetMapping("/listerProduits")
    public String showProductList(Model model){
        model.addAttribute("products", productRepo.findAll());

        // get categories list
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);

        return "produits";
    }

    @GetMapping("/creerProduit")
    public String createProduct(Model model) {

        Product product = this.productRepo.findById(1L).get();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepo.findAll());
        return "produits_info";
    }

    // LIST PRODUCTS BY CATEGORY
    @RequestMapping(value="/listerProduits/category", method = RequestMethod.GET)
    public String showProductListByCategory(Model model, @RequestParam("id") Long id) {
        // get products
        List<Product> products =  productRepo.findByCategoryId(id);
        model.addAttribute("products", products);

        // get categories list
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);

        return "produits";
    }

    // CREATE
    @PostMapping("/sauverProduit")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/creerProduit";
        }

        productRepo.save(product);
        return "produits";
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
