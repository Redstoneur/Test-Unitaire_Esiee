package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Echange;

import fr.knap.testunitaire_esiee.respository.EchangeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchangeService {
    @Autowired
    private EchangeRepository echangeRepository;

    public Echange creerEchange(Echange echange) {
        return echangeRepository.save(echange);
    }

    public List<Echange> obtenirTousLesEchanges() {
        return echangeRepository.findAll();
    }
    public Echange obtenirEchangeParId(Long id) {
        return echangeRepository.findById(id).orElse(null);
    }

    public Echange mettreAJourEchange(Echange echange) {
        return echangeRepository.save(echange);
    }

    public boolean echangeExist(Long id){
        return echangeRepository.existsById(id);
    }

}

