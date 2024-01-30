package ega.backend.ega.controleur;

import ega.backend.ega.dto.AuthentificationDto;
import ega.backend.ega.entites.Utilisateur;
import ega.backend.ega.securite.JwtService;
import ega.backend.ega.services.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UtilisateurService utilisateurService;
    @PostMapping(path = "inscription")
    public void inscription(@RequestBody Utilisateur utilisateur)
    {
        log.info("inscription");
        this.utilisateurService.inscriptionA(utilisateur);
    }
    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String,String> activation)
    {
        log.info("inscription");
        this.utilisateurService.activation(activation);
    }
    @PostMapping(path = "connexion")
    public Map<String, String> connexion(@RequestBody AuthentificationDto authentificationDto)
    {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentificationDto.username(), authentificationDto.password())
        );
        if(authenticate.isAuthenticated()){
           return this.jwtService.generate(authentificationDto.username());
        }
        return null;
    }

}

