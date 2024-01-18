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
import java.util.Map;

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
    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String,String> activation)
    {
        log.info("inscription");
        this.utilisateurService.activation(activation);
    }
}

