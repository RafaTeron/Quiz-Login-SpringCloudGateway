package com.rafaelAbreu.LoginJWT.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rafaelAbreu.LoginJWT.entities.Player;
import com.rafaelAbreu.LoginJWT.feign.PlayerFeign;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private PlayerFeign playerFeign;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    ResponseEntity<Player> response = playerFeign.findByUsuario(username); 
	    if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
	        return response.getBody(); 
	    } else {
	        throw new UsernameNotFoundException("User not found");
	    }
	}
}