package ega.backend.ega.repository;

import ega.backend.ega.entites.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UtilisateurRepository extends CrudRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByEmail(String email);
}
