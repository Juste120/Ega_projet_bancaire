package ega.backend.ega.services;

import ega.backend.ega.entites.Compte;
import ega.backend.ega.entites.Utilisateur;
import ega.backend.ega.repository.CompteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompteService {
    private CompteRepository compteRepository;
    private NotificationService notificationService;

    public void creer(Compte compte){
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        compte.setUtilisateur(utilisateur);
        this.compteRepository.save(compte);
        notificationService.envoyerNumeroCompte(compte);
    }
}
