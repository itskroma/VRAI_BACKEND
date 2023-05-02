package com.example.BACK_END.repositories;

import com.example.BACK_END.models.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {
    Livre findById(int id);
    Livre findByTitre(String titre);
}
