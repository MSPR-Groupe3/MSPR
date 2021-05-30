package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.Contact;
import fr.epsi.mspr.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository repo;

    @GetMapping("/clients")
    public String createContact(Model model) {

        Contact contact = this.repo.findById(1L).get();
        model.addAttribute("Contact", contact);
        return "clients"; }


    // CREATE A CLIENT
    @PostMapping("/clients")
    public String saveContact(@ModelAttribute Contact contact, Model model) {
        this.repo.save(contact);
        model.addAttribute("contact", contact);
        return "clients";
    }
    // SHOW ALL CLIENTS
    @GetMapping("/clients")
    public String showContactList(Model model){
        model.addAttribute("contact", repo.findAll());
        return "clients";
    }


    // EDIT A CLIENT
    @PostMapping("/clients")
    public String updateContact(@ModelAttribute Contact contact, Model model){
        this.repo.save(contact);
        model.addAttribute("contact", contact);
    return "clients";
    }

    // DELETE A CLIENT
    @PostMapping("/clients")
    public String deleteContact(@PathVariable("id") int id, Model model) {

        return "clients";
    }
}
