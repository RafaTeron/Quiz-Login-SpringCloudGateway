package com.rafaelAbreu.JogoQuiz.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.rafaelAbreu.JogoQuiz.entities.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_player")
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Column(unique = true)
    private String usuario;
    private String password;

	private Role roles;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "player_question",
        joinColumns = @JoinColumn(name = "player_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> question = new ArrayList<>();
    
    private List<String> questionRespondidas;

    private Integer pointScore;

    public Player() {
        this.questionRespondidas = new ArrayList<>();
    }

    public Player(Long id, String name, Integer pointScore,Role roles) {
        this.id = id;
        this.name = name;
        this.pointScore = pointScore;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPointScore() {
        return pointScore;
    }

    public void setPointScore(Integer pointScore) {
        this.pointScore = pointScore;
    }
    
	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public List<String> getQuestionRespondidas() {
		return questionRespondidas;
	}
    
    public void setQuestionRespondidas(List<String> questionRespondidas) {
		this.questionRespondidas = questionRespondidas;
	}
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }
}
