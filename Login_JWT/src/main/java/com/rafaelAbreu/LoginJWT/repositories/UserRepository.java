package com.rafaelAbreu.LoginJWT.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelAbreu.LoginJWT.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUsuario(String usuario);
}
