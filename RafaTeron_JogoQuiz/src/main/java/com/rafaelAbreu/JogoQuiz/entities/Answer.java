package com.rafaelAbreu.JogoQuiz.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_answer")
public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answerText;
    private String descricao;
    private boolean isCorrect;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer() {

    }

    public Answer(Long id, String answerText, String descricao, Question question, boolean isCorrect) {
        this.id = id;
        this.answerText = answerText;
        this.descricao = descricao;
        this.question = question;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerText);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Answer other = (Answer) obj;
        return Objects.equals(id, other.id) && Objects.equals(answerText, other.answerText);
    }
}
