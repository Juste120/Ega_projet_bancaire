package ega.backend.ega.controleur;

import ega.backend.ega.dto.OperationFinanciereDTO;
import ega.backend.ega.dto.RetraitDTO;
import ega.backend.ega.dto.VirementDTO;
import ega.backend.ega.entites.Compte;
import ega.backend.ega.services.CompteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "compte")
@AllArgsConstructor
public class CompteControleur {
    private CompteService compteService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void creer(@RequestBody Compte compte){
        this.compteService.creer(compte);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/versement")
    public ResponseEntity<String> effectuerVersement(@RequestBody VirementDTO virementDTO) {
        try {
            compteService.effectuerVersement(virementDTO);
            return ResponseEntity.ok("Versement effectué avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/depot")
    public ResponseEntity<String> effectuerDepot(@RequestBody OperationFinanciereDTO operationFinanciereDTO) {
        try {
            compteService.effectuerDepot(operationFinanciereDTO);
            return ResponseEntity.ok("Dépôt effectué avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/retrait")
    public ResponseEntity<String> effectuerRetrait(@RequestBody RetraitDTO retraitDTO) {
        try {
            compteService.effectuerRetrait(retraitDTO);
            return ResponseEntity.ok("Retrait effectué avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

