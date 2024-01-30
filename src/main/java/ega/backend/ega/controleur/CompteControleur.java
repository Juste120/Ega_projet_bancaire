package ega.backend.ega.controleur;

import ega.backend.ega.entites.Compte;
import ega.backend.ega.services.CompteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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


}
