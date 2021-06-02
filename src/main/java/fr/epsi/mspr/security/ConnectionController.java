package fr.epsi.mspr.security;

import fr.epsi.mspr.entities.Contact;
import fr.epsi.mspr.entities.User;
import fr.epsi.mspr.repositories.UserRepository;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ConnectionController {

    @Autowired
    private UserRepository repo;


    @GetMapping("/login")
    public String accessLoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "connexion"; }


    @PostMapping("/login")
    public String checkLogin(@ModelAttribute User user, Model model){

        // récupération de tous les users de la base pour vérifier
        List<User> lesutilisateurs = repo.findAll();

        String hashedPasswd = user.getPassword(); // hasher le mdp

        for (int i = 0; i < lesutilisateurs.size(); i++) {

            if (user.getLoginEmail() == lesutilisateurs.get(i).getLoginEmail() ) {

                if(user.getPassword() == lesutilisateurs.get(i).getLoginEmail() ) {

                    return "index";

                } else {
                    System.out.println("Mauvais mot de passe");
                    return "connexion";
                }

            } else {
                System.out.println("Aucun utilisateur trouvé");
                return "connexion";
            }
        }

        return "clients";
    }




}
