package com.rafaelAbreu.LoginJWT.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelAbreu.LoginJWT.dto.LoginRequestDTO;
import com.rafaelAbreu.LoginJWT.dto.RegisterRequestDTO;
import com.rafaelAbreu.LoginJWT.dto.ResponseDTO;
import com.rafaelAbreu.LoginJWT.entities.Player;
import com.rafaelAbreu.LoginJWT.infra.security.TokenService;
import com.rafaelAbreu.LoginJWT.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	private UserRepository repository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Player user = this.repository.findByUsuario(body.usuario()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getUsuario(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        Optional<Player> user = this.repository.findByUsuario(body.usuario());

        if(user.isEmpty()) {
            Player newUser = new Player();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setUsuario(body.usuario());
            newUser.setName(body.name());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getUsuario(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
