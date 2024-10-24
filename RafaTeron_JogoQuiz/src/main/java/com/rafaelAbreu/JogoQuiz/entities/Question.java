package com.rafaelAbreu.JogoQuiz.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rafaelAbreu.JogoQuiz.entities.enums.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_question")
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private List<Player> players = new ArrayList<>();


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;


    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    public Question() {

    }


    public Question(Long id, String questionText, Category category, List<Player> players) {
        this.id = id;
        this.questionText = questionText;
        this.category = category;
        this.players = players;
    }

    public Long getId() {
        return id;
    }  

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

   public List<Answer> getAnswers() {
       return answers;
   }

   public void setAnswers(List<Answer> answers) {
       this.answers = answers;
   }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public List<Player> getPlayers() {
		return players;
	}
    
    public void setPlayers(List<Player> players) {
		this.players = players;
	}

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, questionText);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        return Objects.equals(id, other.id) && Objects.equals(questionText, other.questionText);
    }
}