package com.devweb.trans.controller;


import com.devweb.trans.Form.DepotForm;
import com.devweb.trans.Form.EntrepriseForm;
import com.devweb.trans.model.*;
import com.devweb.trans.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin

@RequestMapping(value = "/entreprise")
public class EntrepriseController {
    @Autowired
    EntrepriseService entrepriseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompteService compteService;
    @Autowired
    private DepotService depotService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserDetailsServiceImpl userDetailsService;



    @GetMapping(value = "/entreprise/liste")
    public List<Entreprise> lister(){
        return entrepriseService.findEntreprise();
    }
    @GetMapping(value = "/entreprise/{id}")
    public Entreprise lister(@PathVariable int id) throws Exception {
        Entreprise entreprise = entrepriseService.findById(id).orElseThrow(
                ()->new Exception("Cette entreprise n'existe pas !")
        );
        return entreprise;
    }


    @PostMapping(value = "/entreprise/add")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    public LireAjouter addEntreprise(@RequestBody EntrepriseForm entrepriseForm){
        Entreprise entreprise=new Entreprise(entrepriseForm.getAdresse(),entrepriseForm.getNinea(),entrepriseForm.getRaisonsociale(),"Actif",entrepriseForm.getPhone(),entrepriseForm.getEmailentr());
        entrepriseService.save(entreprise);
        Compte compte=new Compte();
        SimpleDateFormat formater = new SimpleDateFormat("yyMM ddhh mmss");
        Date now=new Date();
        String numerocompte=formater.format(now);
        compte.setNumerocompte(numerocompte);
        compte.setEntreprise(entreprise);
        compte.setSolde(0);
        compteService.save(compte);
        User user=new User(entrepriseForm.getName(),entrepriseForm.getUsername(),entrepriseForm.getEmail(),entrepriseForm.getPassword(),entrepriseForm.getImage(),entrepriseForm.getTelephone(),entrepriseForm.getNci(),"Actif");
        user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet <>();
        Role role=new Role();
        role.setId(entrepriseForm.getRole());
        roles.add(role);
        user.setRoles(roles);
        user.setEntreprise(entreprise);
        user.setCompte(compte);
        userService.save(user);
        String msg="L'entreprise "+ entreprise.getRaisonsociale()+" a été ajouté !! ";
        String msgCompte="Le compte numéro "+compte.getNumerocompte()+"  lui appartient";
        LireAjouter lire = new LireAjouter(200,msg,msgCompte);
        return lire;
    }

    @PostMapping(value = "/depot", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyAuthority('ROLE_CAISSIER')")
    public Depot depot(@RequestBody DepotForm depotForm) throws Exception { // @RequestBody pour envoyer la réponse String pour cette requête Web
        User caissier = userDetailsService.getUserConnecte();
        Depot depot=new Depot();
        depot.setCaissier(caissier);
        Compte compte=compteService.findCompteByNumerocompte(depotForm.getNumerocompte()).orElseThrow(
                ()-> new Exception("Ce compte n'existe pas !")
        );
        if(compte.getEntreprise().getRaisonsociale().equals("WAri")){
            throw new Exception("Depot impossible sur ce compte");
        }

        depot.setCompte(compte);
        depot.setDate(new Date());
        depot.setMontant(depotForm.getMontant());
        compte.setSolde((int) (compte.getSolde()+depotForm.getMontant()));
        compteService.save(compte);
        return depotService.save(depot);
    }

    @GetMapping(value = "/bloque/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    public Lire bloque(@PathVariable int id) throws Exception{ // @PathVariable pour mapper la variable URI à l'un des arguments de la méthode
        Entreprise entreprise=entrepriseService.findById(id).orElseThrow(
                ()-> new Exception("Ce partenaire n'existe pas !!")
        );
        if(entreprise.getRaisonsociale().equals("Wari")){
            throw new Exception("Impossible de le bloquer !");
        }
        String msg;
        if(entreprise.getStatus().equals("Actif")){
            entreprise.setStatus("Bloqué");
            msg="Bloqué";
        }
        else {
            entreprise.setStatus("Actif");
            msg="Débloqué";
        }
        entrepriseService.save(entreprise);
        Lire lire =new Lire(200,msg);
        return  lire;
    }

    @GetMapping(value = "/bloqueU/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','ROLE_ADMINPRINCIPAL','ROLE_ADMIN')")
    public Lire bloqueUser(@PathVariable int id) throws Exception{
        User user=userService.findById(id).orElseThrow(
                ()-> new Exception("Cet utilisateur n'existe pas !!")
        );
        User userConnecte=userDetailsService.getUserConnecte();
        if(userConnecte==user){
            throw new Exception("Impossible de se bloquer soit même !");
        }
        //else if(user.getEntreprise()!=userConnecte.getEntreprise()){
           // throw new Exception("Impossible de bloquer cet utilisateur !");
        //}
        else if(user.getId()==1){
            throw new Exception("Impossible de bloquer le Super-Admin");
        }

        String msg;
        if(user.getStatus().equals("Actif")){
            user.setStatus("Bloqué");
            msg="Bloqué";
        }
        else {
            user.setStatus("Actif");
            msg="Débloqué";
        }

        userService.save(user);

        Lire lire=new Lire(200,msg);
        return lire;
    }

}
