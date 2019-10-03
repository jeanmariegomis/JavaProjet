package com.devweb.trans.service;

import com.devweb.trans.model.Compte;
import com.devweb.trans.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    CompteRepository compteRepository;

    public Compte save(Compte compte){
        return compteRepository.save(compte);
    }

    public List<Compte> findAll(){
        return compteRepository.findAll();
    }
    public Optional<Compte> findById(int id){
        return compteRepository.findById(id);
    }
    public Optional<Compte> findByNumerocompte(String numerocompte){
        return compteRepository.findCompteByNumerocompte(numerocompte);
    }
}
