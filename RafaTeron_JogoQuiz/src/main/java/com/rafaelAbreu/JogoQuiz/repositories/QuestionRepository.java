package com.rafaelAbreu.JogoQuiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rafaelAbreu.JogoQuiz.entities.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>{

    @Query(value = "SELECT * FROM tb_question ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Question encontrarQuestionAleatoria();
}
