package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.Organization;
import fr.epsi.mspr.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class OrganizationController {

    @Autowired
    private OrganizationRepository repoOrga;

    @GetMapping("/creerOrganisation")
    public String createOrganization(Model model) {

        Organization organization = this.repoOrga.findById(1L).get();
        model.addAttribute("organization", organization);
        return "organisations_info"; }

    // CREATE AN ORGANIZATION
    @PostMapping("/sauverOrganisation")
    public String saveOrganization(@ModelAttribute Organization organization, Model model) {
        this.repoOrga.save(organization);
        model.addAttribute("organization", organization);
        return "organisations_info";
    }

    // SHOW ALL ORGANIZATIONS
    @GetMapping("/listerOrganisations")
    public String showOrganizationList(Model model){
        model.addAttribute("organization", repoOrga.findAll());
        return "organisations";
    }

    // EDIT AN ORGANIZATION
    @PostMapping("/modifierOrganisation")
    public String updateOrganization(@ModelAttribute Organization organization, Model model){
        this.repoOrga.save(organization);
        model.addAttribute("organization", organization);
        return "commandes_info";
    }

    // DELETE AN ORGANIZATION
    @PostMapping("/supprimerOrganisation")
    public String deleteOrganization(@PathVariable("id") int id, Model model) {

        return "commandes_info";
    }
}
