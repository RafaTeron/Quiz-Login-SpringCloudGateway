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

import com.rafaelAbreu.JogoQuiz.entities.Answer;
import com.rafaelAbreu.JogoQuiz.services.AnswerService;

@RestController
@RequestMapping(value = "/answers")
public class AnswerResources {
    
    @Autowired
    private AnswerService answerService;

    @GetMapping
    public ResponseEntity<List<Answer>> findAll(){
        List<Answer> list = answerService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Answer> findById(@PathVariable Long id) throws RuntimeException{
        Answer answer = answerService.findById(id);
        return ResponseEntity.ok().body(answer);
    }

    
	@PostMapping
	public ResponseEntity<Answer> insert(@RequestBody Answer objAnswer) {
		Answer obj = answerService.insert(objAnswer);
		return ResponseEntity.ok().body(obj);
	}

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id){
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Answer> update(@PathVariable Long id, @RequestBody Answer answer) {
        answer = answerService.update(id, answer);
        return ResponseEntity.ok().body(answer);
    }
}
