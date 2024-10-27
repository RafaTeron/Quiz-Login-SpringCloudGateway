package com.rafaelAbreu.LoginJWT.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelAbreu.LoginJWT.entities.Player;
import com.rafaelAbreu.LoginJWT.entities.dto.AuthenticationDTO;
import com.rafaelAbreu.LoginJWT.entities.dto.LoginResponseDTO;
import com.rafaelAbreu.LoginJWT.infra.security.TokenService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	
    @Autowired
    private AuthenticationManager authenticationManager;    
    
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.usuario(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Player) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); 
        }
    }
}
