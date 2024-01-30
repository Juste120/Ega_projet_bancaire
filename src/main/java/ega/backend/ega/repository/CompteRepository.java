package ega.backend.ega.repository;

import ega.backend.ega.entites.Compte;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository extends CrudRepository<Compte, Integer> {
}
