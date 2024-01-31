package ega.backend.ega.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationFinanciereDTO {
    private String numeroCompte;
    private String numeroCompteDestinataire;
    private double montant;
}
