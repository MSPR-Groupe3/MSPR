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
        // list all sellable products
        model.addAttribute("products", productRepo.findByIsSellableTrue());

        // get categories list
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);

        return "produits";
    }

    // LIST PRODUCTS BY CATEGORY
    @RequestMapping(value="/listerProduits/category", method = RequestMethod.GET)
    public String showProductListByCategory(Model model, @RequestParam("id") Long id) {
        // get products
        List<Product> products =  productRepo.findByCategoryIdAndIsSellableTrue(id);
        model.addAttribute("products", products);

        // get categories list
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);

        return "produits";
    }

    // READ ONE
    @GetMapping("/detailsProduit/{id}")
    public String showProduct(@PathVariable("id") long id, Model model){
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id:" + id));
        model.addAttribute("product", product);

        return "produits_info";
    }

    // CREATE NEW
    @GetMapping("/creerProduit")
    public String createProduct(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);

        model.addAttribute("categories", categoryRepo.findAll());
        return "produits_create";
    }

    // SAVE NEW
    @PostMapping("/sauverProduit")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/creerProduit";
        }

        productRepo.save(product);
        return "redirect:/listerProduits";
    }

    // UPDATE
    @GetMapping("/modifierProduit/{id}")
    public String updateProduct(@PathVariable("id") long id, Model model) {
        Product product= productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepo.findAll());

        return "produits_modif";
    }

    // SAVE UPDATE
    @PostMapping("/majProduit/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid Product product,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "redirect:/modifierProduit/{id}";
        }
        productRepo.save(product);
        return "redirect:/listerProduits";
    }

    // DELETE
    @GetMapping("/supprimerProduit/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        // to keep product information in DB, it is only disabled for sale
        product.setSellable(false);
        productRepo.save(product);
        return "produits";
    }

}
