package com.devweb.trans.service;

import com.devweb.trans.model.Depot;
import com.devweb.trans.repository.DepotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepotService {
    @Autowired
    DepotRepository depotRepository;

    public Depot save(Depot depot){
        return depotRepository.save(depot);
    }

    public List<Depot> findAll(){
        return depotRepository.findAll();
    }
    public Optional<Depot> findById(int id){
        return depotRepository.findById(id);
    }
}
