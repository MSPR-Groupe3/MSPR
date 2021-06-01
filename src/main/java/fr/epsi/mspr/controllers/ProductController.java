package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.Product;
import fr.epsi.mspr.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {
    /*
        all return to update according to HTML files
     */

    private ProductRepository productRepo;

    // SHOW ALL PRODUCTS
    @GetMapping("/indexProducts")
    public String showProductList(Model model){
        model.addAttribute("products", productRepo.findAll());
        return "produits";
    }

    // SHOW PRODUCTS BY CATEGORY
    @GetMapping("/CategoryProducts")
    public String showCategoryProductsList(Model model, int category_id){
        model.addAttribute("productsByCategory", productRepo.findByCategoryId(category_id));
        return "produits";
    }

    // CREATE
    @PostMapping("/addProduct")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-product";
        }

        productRepo.save(product);
        return "redirect:/indexProducts";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") long id, Model model) {
        Product product= productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);
        return "update-product";
    }

    // DELETE
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        productRepo.delete(product);
        return "produits";
    }
}
