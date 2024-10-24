package com.rafaelAbreu.JogoQuiz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelAbreu.JogoQuiz.entities.Question;
import com.rafaelAbreu.JogoQuiz.repositories.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findById(Long id) throws RuntimeException {
        Optional<Question> obj = questionRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        } else {
            throw new RuntimeException("Question não encontrado");
        }
    }

    public Question insert(Question question) {
		return questionRepository.save(question);
	}

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Question update(Long id, Question question){
        Question entity = questionRepository.getReferenceById(id);
        updateDaTa(entity, question);
        return questionRepository.save(entity);
    }

    protected void updateDaTa(Question entity, Question question) {
        entity.setQuestionText(question.getQuestionText());
    }
    
}
