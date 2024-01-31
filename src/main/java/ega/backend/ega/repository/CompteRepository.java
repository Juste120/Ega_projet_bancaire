package ega.backend.ega.repository;

import ega.backend.ega.entites.Compte;
import ega.backend.ega.entites.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompteRepository extends CrudRepository<Compte, Integer> {
    Optional<Utilisateur> findByEmail(String email);

    Optional<Object> findByNumeroCompte(String numeroCompte);
}
