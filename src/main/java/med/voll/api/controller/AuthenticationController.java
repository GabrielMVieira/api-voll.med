package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.AuthenticationDTO;
import med.voll.api.domain.user.LoginRespondeDTO;
import med.voll.api.domain.user.User;
import med.voll.api.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(),dto.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = service.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginRespondeDTO(tokenJWT));
    }
}
