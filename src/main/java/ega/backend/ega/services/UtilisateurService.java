package ega.backend.ega.services;

import ega.backend.ega.Enum.TypeDeRole;
import ega.backend.ega.entites.Role;
import ega.backend.ega.entites.Utilisateur;
import ega.backend.ega.entites.Validation;
import ega.backend.ega.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UtilisateurService implements UserDetailsService {
    private UtilisateurRepository utilisateurRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;



    public void inscription(Utilisateur utilisateur){
        if (utilisateur.getEmail().indexOf('@') == -1){
            throw new RuntimeException("Votre mail est incorrect");
        }
        if (utilisateur.getEmail().indexOf('.') == -1){
            throw new RuntimeException("Votre mail est incorrect");
        }
        Optional<Utilisateur> utilisateurOptional =this.utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (utilisateurOptional.isPresent()){
            throw new RuntimeException("Votre mail est déja utilisé");
        }
        String passwordCrypté = this.passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(passwordCrypté);
        Role roleUtilisateur = new Role();
        roleUtilisateur.setLibelle(TypeDeRole.UTILISATEUR);
        utilisateur.setRole(roleUtilisateur);
        utilisateur = this.utilisateurRepository.save(utilisateur);
        this.validationService.enregistrer(utilisateur);
    }

    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.lireEnFonctionDuCode(activation.get("code"));
        if (Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("Votre code est expiré");
        }
        Utilisateur utilisateurActiver = this.utilisateurRepository.findById(validation.getUtilisateur().getId()).orElseThrow(() -> new RuntimeException("utilisateur inconnu"));
        utilisateurActiver.setActif(true);
        this.utilisateurRepository.save(utilisateurActiver);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurRepository
                .findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur ne corresponds à cette identifiant"));

    }
}

