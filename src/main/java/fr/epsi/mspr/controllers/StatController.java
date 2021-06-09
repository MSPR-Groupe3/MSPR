package fr.epsi.mspr.controllers;

import fr.epsi.mspr.entities.Organization;
import fr.epsi.mspr.entities.ProductInPurchase;
import fr.epsi.mspr.entities.ProductInPurchaseKey;
import fr.epsi.mspr.entities.Purchase;
import fr.epsi.mspr.repositories.OrganizationRepository;
import fr.epsi.mspr.repositories.ProductInPurchaseRepository;
import fr.epsi.mspr.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StatController {

    @Autowired
    private ProductInPurchaseRepository productInPurchaseRepo;
    @Autowired
    private PurchaseRepository purchaseRepo;

    @Autowired
    private OrganizationRepository organizationRepo;

    @GetMapping("/statistiques")
    public String showStatistics(Model model){


        List<Purchase> purchases = purchaseRepo.findAll();
        // biggest value of the all commandes
        float maxTotalValue = 0;
        // id of the biggest value
        Long idMaxTotalValue = purchases.get(0).getId();
        for(Purchase purchase: purchases){
            if(maxTotalValue <= productInPurchaseRepo.getTotalValue(purchase.getId())){
                idMaxTotalValue = purchase.getId();
                maxTotalValue = productInPurchaseRepo.getTotalValue(idMaxTotalValue);
            }
        }
        String referenceCommande = purchaseRepo.getOne(idMaxTotalValue).getReference();
        model.addAttribute("referenceCommande", referenceCommande);
        model.addAttribute("maxTotalValue", maxTotalValue);


        // Amount of commande per organisation
        HashMap<String, Integer> nbPurchaseByOrganization = new HashMap<String, Integer>();
        List<Organization> organizations = organizationRepo.findAll();
        for (Organization organization: organizations){
            int nbPurchase = purchaseRepo.countPurchaseByContact_Organization_Id(organization.getId());
            nbPurchaseByOrganization.put(organization.getCompanyName(), nbPurchase);
        }
        model.addAttribute("nbPurchaseByOrganization", nbPurchaseByOrganization);

        // Horizontal bar data for : top Organization by number of Purchase
        List<List<Object>> dataNbPurchase = purchaseRepo.getTopOrganizationByNbPurchase();
        List<String> label = new ArrayList<>();
        List<BigInteger> nbPurchase = new ArrayList<>();
        for(List<Object> row : dataNbPurchase) {
            label.add(row.get(0).toString());
            nbPurchase.add((BigInteger) (row.get(1)));
        }
        model.addAttribute("label", label);
        model.addAttribute("nbPurchase", nbPurchase);


        return "stats";
    }
}
