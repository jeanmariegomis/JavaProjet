package com.devweb.trans.repository;

import com.devweb.trans.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    @Query("SELECT e FROM Entreprise e WHERE e.raisonsociale <> 'Transfert' AND e.raisonsociale <> 'Etat du Sénégal' ")
    public List<Entreprise> findEntreprise();
}