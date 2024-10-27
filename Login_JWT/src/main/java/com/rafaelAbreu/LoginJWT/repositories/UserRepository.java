package com.rafaelAbreu.LoginJWT.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelAbreu.LoginJWT.entities.Player;

public interface UserRepository extends JpaRepository<Player, String> {

	Optional<Player> findByUsuario(String usuario);
}
