package com.rafaelAbreu.JogoQuiz.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelAbreu.JogoQuiz.entities.Player;

public interface PlayerRepository extends JpaRepository<Player,Long>{

	Player findByUsuario(String usuario);
}
