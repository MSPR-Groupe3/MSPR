package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.Organization;
import fr.epsi.mspr.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrganizationController {

    @Autowired
    private OrganizationRepository repoOrga;

    @GetMapping("/creerOrganisation")
    public String createOrganization(Model model) {

        Organization organization = new Organization();
        model.addAttribute("organization", organization);
        return "organisations_create"; }

    // CREATE AN ORGANIZATION
    @PostMapping("/sauverOrganisation")
    public String saveOrganization(@ModelAttribute Organization organization, Model model) {
        this.repoOrga.save(organization);
        model.addAttribute("organization", organization);
        return "organisations_create";
    }

    // SHOW ALL ORGANIZATIONS
    @GetMapping("/listerOrganisations")
    public String showOrganizationList(Model model){
        model.addAttribute("organizations", repoOrga.findAll());
        return "organisations";
    }

    // SHOW ONE
    @GetMapping("/detailsOrganisation/{id}")
    public String showOrganization(@PathVariable("id") long id, Model model){
        Organization organization = repoOrga.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid organization id:" + id));
        model.addAttribute("organization", organization);

        return "organisations_info";
    }

    // EDIT AN ORGANIZATION
    @PostMapping("/modifierOrganisation")
    public String updateOrganization(@ModelAttribute Organization organization, Model model){
        this.repoOrga.save(organization);
        model.addAttribute("organization", organization);
        return "organisations_create";
    }

    // DELETE AN ORGANIZATION
    @PostMapping("/supprimerOrganisation")
    public String deleteOrganization(@PathVariable("id") int id, Model model) {

        return "organisations_create";
    }
}
