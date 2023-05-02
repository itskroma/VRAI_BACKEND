package com.example.BACK_END.service;

import com.example.BACK_END.models.Livre;
import com.example.BACK_END.repositories.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public boolean ajouterLivre(Livre livre) {
        if(livre.getTitre().isEmpty() || livre.getAuteur().isEmpty()){
            return false;
        }
        Livre livreExistant = livreRepository.findByTitre(livre.getTitre());
        if (livreExistant != null) {
            return false;
        } else {
            livreRepository.save(livre);
            return true;
        }
    }

    public boolean supprimerLivre(int id) {
        Livre livre = livreRepository.findById(id);
        if (livre != null) {
            livreRepository.delete(livre);
            return true;
        }
        return false;
    }

    public boolean modifierLivre(int id, String nouveauTitre, String nouveauAuteur) {
        Livre livre = livreRepository.findById(id);
        if (livre == null) {
            return false;
        }
        if (!nouveauTitre.isEmpty()) {
            livre.setTitre(nouveauTitre);
        }
        if (!nouveauAuteur.isEmpty()) {
            livre.setAuteur(nouveauAuteur);
        }
        livreRepository.save(livre);
        return true;
    }

    public List<Livre> getLivres() {
        return livreRepository.findAll();
    }
}
