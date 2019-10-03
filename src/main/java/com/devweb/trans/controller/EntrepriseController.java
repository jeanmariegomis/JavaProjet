package com.devweb.trans.controller;


import com.devweb.trans.Form.EntrepriseForm;
import com.devweb.trans.model.*;
import com.devweb.trans.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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
    PasswordEncoder encoder;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

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
        compte.setSolde("0");
        compteService.save(compte);
        User user=new User(entrepriseForm.getName(),entrepriseForm.getUsername(),entrepriseForm.getEmail(),entrepriseForm.getPassword(),entrepriseForm.getImage(),entrepriseForm.getTelephone(),entrepriseForm.getNci(),"Actif");
        user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role role=new Role();
        role.setId(3);
        roles.add(role);
        user.setRoles(roles);
        user.setEntreprise(entreprise);
        userService.save(user);
        String msg="L'entreprise "+ entreprise.getRaisonsociale()+" ainsi que son admin principal ont bien été ajouté !! ";
        String msgCompte="Le compte numéro "+compte.getNumerocompte()+"  lui a été assigné";
        LireAjouter lire = new LireAjouter(200,msg,msgCompte);
        return lire;
    }


}
