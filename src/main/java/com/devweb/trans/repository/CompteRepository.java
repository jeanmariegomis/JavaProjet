package com.devweb.trans.repository;

import com.devweb.trans.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
    public Optional<Compte> findCompteByNumerocompte(String numerocompte);
}