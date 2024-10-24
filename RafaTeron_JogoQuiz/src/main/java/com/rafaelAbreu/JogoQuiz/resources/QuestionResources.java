package com.rafaelAbreu.JogoQuiz.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rafaelAbreu.JogoQuiz.entities.Question;
import com.rafaelAbreu.JogoQuiz.services.QuestionService;

@RestController
@RequestMapping(value = "/quiz/question")
public class QuestionResources {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> findAll(){
        List<Question> list = questionService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Question> findById(@PathVariable Long id) throws RuntimeException{
        Question question = questionService.findById(id);
        return ResponseEntity.ok().body(question);
    }

    
	@PostMapping
	public ResponseEntity<Question> insert(@RequestBody Question objQuestion) {
		Question obj = questionService.insert(objQuestion);
		return ResponseEntity.ok().body(obj);
	}

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody Question question) {
        question = questionService.update(id, question);
        return ResponseEntity.ok().body(question);
    }
    
}
