package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.Contact;
import fr.epsi.mspr.entities.Product;
import fr.epsi.mspr.repositories.ContactRepository;
import fr.epsi.mspr.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepo;

    @Autowired
    private OrganizationRepository orgaRepo;

    @GetMapping("/creerClient")
    public String createContact(Model model) {

        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        model.addAttribute("organizations", orgaRepo.findAll());
        return "clients_create"; }


    // CREATE A CLIENT
    @PostMapping("/sauverClient")
    public String saveContact(@ModelAttribute Contact contact, Model model) {
        this.contactRepo.save(contact);
        model.addAttribute("contact", contact);
        return "clients";
    }
    // SHOW ALL CLIENTS
    @GetMapping("/listerClients")
    public String showContactList(Model model){
        model.addAttribute("contacts", contactRepo.findAll());
        return "clients";
    }

    // SHOW ONE
    @GetMapping("/detailsClient/{id}")
    public String showContact(@PathVariable("id") long id, Model model){
        Contact contact = contactRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid contact id:" + id));
        model.addAttribute("contact", contact);

        return "clients_info";
    }

    // UPDATE
    @GetMapping("/modifierClient/{id}")
    public String updateContact(@PathVariable("id") long id, Model model) {
        Contact contact = contactRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));

        model.addAttribute("contact", contact);
        model.addAttribute("organizations", orgaRepo.findAll());

        return "clients_modif";
    }

    // SAVE UPDATE
    @PostMapping("/majClient/{id}")
    public String updateContact(@PathVariable("id") long id, @Valid Contact contact,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            contact.setId(id);
            return "redirect:/modifierClient/{id}";
        }
        contactRepo.save(contact);
        return "redirect:/listerClients";
    }


    // EDIT A CLIENT
    @PostMapping("/modifierClient")
    public String updateContact(@ModelAttribute Contact contact, Model model){
        this.contactRepo.save(contact);
        model.addAttribute("contact", contact);
    return "clients_create";
    }

    // DELETE A CLIENT
    @PostMapping("/supprimerClient")
    public String deleteContact(@PathVariable("id") int id, Model model) {

        return "clients_create";
    }
}
