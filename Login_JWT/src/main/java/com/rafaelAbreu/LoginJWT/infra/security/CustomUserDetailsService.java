package com.rafaelAbreu.LoginJWT.infra.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rafaelAbreu.LoginJWT.entities.Player;
import com.rafaelAbreu.LoginJWT.repositories.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player user = this.repository.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getPassword(), new ArrayList<>());
    }
}