package ega.backend.ega.services;

import ega.backend.ega.entites.Utilisateur;
import ega.backend.ega.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository;

    public void inscription(Utilisateur utilisateur){
        this.utilisateurRepository.save(utilisateur);

    }
}

