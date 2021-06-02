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

    @GetMapping("/creerClient")
    public String createContact(Model model) {

        Contact contact = this.repo.findById(1L).get();
        model.addAttribute("Contact", contact);
        return "clients_info"; }


    // CREATE A CLIENT
    @PostMapping("/sauverClient")
    public String saveContact(@ModelAttribute Contact contact, Model model) {
        this.repo.save(contact);
        model.addAttribute("contact", contact);
        return "clients_info";
    }
    // SHOW ALL CLIENTS
    @GetMapping("/listerClients")
    public String showContactList(Model model){
        model.addAttribute("contact", repo.findAll());
        return "clients";
    }


    // EDIT A CLIENT
    @PostMapping("/modifierClient")
    public String updateContact(@ModelAttribute Contact contact, Model model){
        this.repo.save(contact);
        model.addAttribute("contact", contact);
    return "clients_info";
    }

    // DELETE A CLIENT
    @PostMapping("/supprimerClient")
    public String deleteContact(@PathVariable("id") int id, Model model) {

        return "clients_info";
    }
}
