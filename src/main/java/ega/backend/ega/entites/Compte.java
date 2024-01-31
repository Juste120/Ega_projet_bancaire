package ega.backend.ega.entites;

import lombok.extern.slf4j.Slf4j;
import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;
import ega.backend.ega.Enum.TypeCompte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Slf4j
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nom;
    private  String prénom;
    private  String date_de_naissance;
    private  String sexe;
    private  String adresse;
    private  String téléphone;
    private  String email;
    private String password;
    private  String natonnalite;
    private  String numeroCompte;
    private TypeCompte typeCompte;
    private LocalDate Date_creaion;
    private long solde;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id") // Nom de la colonne de clé étrangère dans la table Compte
    private Utilisateur utilisateur;



    private String generateRandomNumeroCompte() {
        // Générer un numéro de compte aléatoire
        Random random = new Random();
        int randomNumber = random.nextInt(1000000);

        // Utiliser la classe String.format pour formater le code du pays en majuscules
        String countryCode = "AC";
        String formattedNumeroCompte = countryCode + String.format("%06d", randomNumber);

        // Assurez-vous que le code du pays est en majuscules
        formattedNumeroCompte = formattedNumeroCompte.toUpperCase();

        // Journaliser le numéro de compte
        log.info("Numéro de compte généré : {}", formattedNumeroCompte);

        return formattedNumeroCompte;
    }

    @PrePersist
    public void initializeDefaults() {
        // Initialiser le solde à zéro
        this.solde = 0;

        // Générer le numéro de compte aléatoire
        this.numeroCompte = generateRandomNumeroCompte();

        // Valider le numéro de compte IBAN
        try {
            validateIban(this.numeroCompte);
        } catch (IbanFormatException e) {
            // Gérer l'exception si la validation échoue
            e.printStackTrace();
        }
    }


    private void validateIban(String numeroCompte) throws IbanFormatException {
        // Assurez-vous que le code de pays est valide pour vos besoins
        String countryCode = "AC";
        if (!numeroCompte.startsWith(countryCode)) {
            throw new IbanFormatException("Iban contains non-existing country code.");
        }

        // Valider l'IBAN (avec prise en compte du code de pays généré)
        IbanUtil.validate(numeroCompte);

        // Vérifier que le code de pays est en majuscules
        if (!Character.isUpperCase(countryCode.charAt(0)) || !Character.isUpperCase(countryCode.charAt(1))) {
            throw new IbanFormatException("Generated country code must contain upper case letters.");
        }
    }





}
