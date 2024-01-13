package ega.backend.ega.controleur;

import ega.backend.ega.entites.Utilisateur;
import ega.backend.ega.services.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurControleur {
    private UtilisateurService utilisateurService;
    @PostMapping(path = "inscription")
    public void inscription(@RequestBody Utilisateur utilisateur)
    {
        log.info("inscription");
        this.utilisateurService.inscription(utilisateur);
    }
}

