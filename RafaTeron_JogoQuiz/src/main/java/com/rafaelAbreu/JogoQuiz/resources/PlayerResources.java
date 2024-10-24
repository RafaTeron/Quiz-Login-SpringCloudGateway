package com.rafaelAbreu.JogoQuiz.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelAbreu.JogoQuiz.entities.Player;
import com.rafaelAbreu.JogoQuiz.exceptions.ErroScoreException;
import com.rafaelAbreu.JogoQuiz.services.PlayerService;

@RestController
@RequestMapping(value = "/quiz/players")
public class PlayerResources {
    
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> findAll(){
        List<Player> list = playerService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id) throws RuntimeException{
        Player player = playerService.findById(id);
        return ResponseEntity.ok().body(player);
    }

    
	@PostMapping
	public ResponseEntity<Player> insert(@RequestBody Player objPlayer) {
		Player obj = playerService.insert(objPlayer);
		return ResponseEntity.ok().body(obj);
	}

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Player> update(@PathVariable Long id, @RequestBody Player player) {
        player = playerService.update(id, player);
        return ResponseEntity.ok().body(player);
    }
    
    @GetMapping(value ="/{id}/conferirResposta")
    public ResponseEntity<Boolean> conferirResposta(@PathVariable Long id, Long opcao) throws ErroScoreException {
        boolean respostaCorreta = playerService.conferirResposta(id, opcao);
        
        return ResponseEntity.ok(respostaCorreta);
    }

    @PostMapping(value = "/{id}/gerarQuestion")
    public ResponseEntity<Void> gerarQuestionParaPlayer(@PathVariable Long id){
        playerService.gerarQuestionParaPlayer(id);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}/reset-quiz")
    public ResponseEntity<Void> resetQuizPlayer(@PathVariable Long id) {
        playerService.resetarQuizPlayer(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/limitePerguntasRespondidas")
    public ResponseEntity<Boolean> verificarLimitePerguntasRespondidas(@PathVariable Long id) {
        boolean limiteAtingido = playerService.limiteDeQuestionsRespondidas(id);
        return ResponseEntity.ok(limiteAtingido);
    }
}
