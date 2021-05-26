package fr.epsi.mspr.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ProductController {

    //private ProductRepository;

    @GetMapping("/indexProducts")
    public String showProductList(Model model){

        return null;
    }
}
