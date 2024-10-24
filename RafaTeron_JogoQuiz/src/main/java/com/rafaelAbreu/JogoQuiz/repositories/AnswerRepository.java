package com.rafaelAbreu.JogoQuiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelAbreu.JogoQuiz.entities.Answer;

public interface AnswerRepository extends JpaRepository<Answer,Long>{
    
}
