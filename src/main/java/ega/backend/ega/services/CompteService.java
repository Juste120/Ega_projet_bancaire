package ega.backend.ega.services;

import ega.backend.ega.dto.OperationFinanciereDTO;
import ega.backend.ega.dto.RetraitDTO;
import ega.backend.ega.dto.VirementDTO;
import ega.backend.ega.entites.Compte;
import ega.backend.ega.entites.Utilisateur;

import ega.backend.ega.exceptions.OperationFinanciereException;
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
    public void effectuerVersement(VirementDTO virementDTO) {
        try {
            validateMontant(virementDTO.getMontant());
        } catch (OperationFinanciereException e) {
            throw new RuntimeException(e.getMessage());
        }

        Compte compte = (Compte) compteRepository.findByNumeroCompte(virementDTO.getNumeroCompte())
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        // Effectuez le versement
        double nouveauSolde = compte.getSolde() + virementDTO.getMontant();
        compte.setSolde((long) nouveauSolde);

        compteRepository.save(compte);
    }

    private void validateMontant(double montant) {
        if (montant < 0) {
            throw new OperationFinanciereException("Le montant ne peut pas être négatif");
        }
    }
    public void effectuerDepot(OperationFinanciereDTO operationFinanciereDTO) {
        try {
            validateMontant(operationFinanciereDTO.getMontant());
        } catch (OperationFinanciereException e) {
            throw new RuntimeException(e.getMessage());
        }

        Compte compteDebiteur = (Compte) compteRepository.findByNumeroCompte(operationFinanciereDTO.getNumeroCompte())
                .orElseThrow(() -> new RuntimeException("Compte débiteur non trouvé"));

        Compte crediteur = (Compte) compteRepository.findByNumeroCompte(operationFinanciereDTO.getNumeroCompteDestinataire())
                .orElseThrow(() -> new RuntimeException("Compte créditeur non trouvé"));

        // Vérifiez si le solde est suffisant pour le dépôt
        if (compteDebiteur.getSolde() < operationFinanciereDTO.getMontant()) {
            throw new OperationFinanciereException("Solde insuffisant pour effectuer le dépôt");
        }

        // Effectuez le dépôt
        double nouveauSoldeDebiteur = compteDebiteur.getSolde() - operationFinanciereDTO.getMontant();
        double nouveauSoldeCrediteur = crediteur.getSolde() + operationFinanciereDTO.getMontant();

        compteDebiteur.setSolde((long) nouveauSoldeDebiteur);
        crediteur.setSolde((long) nouveauSoldeCrediteur);

        compteRepository.save(compteDebiteur);
        compteRepository.save(crediteur);
    }
    public void effectuerRetrait(RetraitDTO retraitDTO) {
        try {
            validateMontant(retraitDTO.getMontant());
        } catch (OperationFinanciereException e) {
            throw new RuntimeException(e.getMessage());
        }

        Compte compte = (Compte) compteRepository.findByNumeroCompte(retraitDTO.getNumeroCompte())
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        // Vérifiez si le solde est suffisant pour le retrait
        if (compte.getSolde() < retraitDTO.getMontant()) {
            throw new OperationFinanciereException("Solde insuffisant pour effectuer le retrait");
        }

        // Effectuez le retrait
        double nouveauSolde = compte.getSolde() - retraitDTO.getMontant();
        compte.setSolde((long) nouveauSolde);

        compteRepository.save(compte);
    }


}


