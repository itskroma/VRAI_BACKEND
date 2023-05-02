package com.example.BACK_END.controllers;

import com.example.BACK_END.models.Livre;
import com.example.BACK_END.service.LivreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LivreController {

    private LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<String> ajouterLivre(@RequestBody Livre livre) {
        if (livreService.ajouterLivre(livre)) {
            return ResponseEntity.ok().body("{\"message\": \"Le livre a été ajouté avec succès !\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"Ce titre existe déjà dans la liste !\"}");
        }
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerLivre(@PathVariable("id") int id) {
        if (livreService.supprimerLivre(id)) {
            return ResponseEntity.ok().body("{\"message\": \"Le livre a été supprimé avec succès !\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"Le livre avec l'id spécifié n'a pas été trouvé.\"}");
        }
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> modifierLivre(@PathVariable("id") int id, @RequestBody Livre livre) {
        if (livreService.modifierLivre(id, livre.getTitre(), livre.getAuteur())) {
            return ResponseEntity.ok().body("{\"message\": \"Le livre a été modifié avec succès !\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"Le livre avec l'id spécifié n'a pas été trouvé.\"}");
        }
    }

    @GetMapping("/getAllLivres")
    public ResponseEntity<List<Livre>> getAllLivres() {
        List<Livre> livres = livreService.getLivres();
        if (livres.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(livres);
        }
    }
}
