package com.devweb.trans.service;

import com.devweb.trans.model.Entreprise;
import com.devweb.trans.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EntrepriseService {
    @Autowired
    EntrepriseRepository entrepriseRepository;

    public Entreprise save(Entreprise entreprise){ return entrepriseRepository.save(entreprise); }

    public List<Entreprise> findAll(){
        return entrepriseRepository.findAll();
    }
    public Optional<Entreprise> findById(int id){
        return entrepriseRepository.findById(id);
    }
    public List<Entreprise> findEntreprise(){return entrepriseRepository.findEntreprise(); }
}
