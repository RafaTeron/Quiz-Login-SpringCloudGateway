package com.rafaelAbreu.JogoQuiz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelAbreu.JogoQuiz.entities.Answer;
import com.rafaelAbreu.JogoQuiz.repositories.AnswerRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository AnswerRepository;

    public List<Answer> findAll() {
        return AnswerRepository.findAll();
    }

    public Answer findById(Long id) throws RuntimeException {
        Optional<Answer> obj = AnswerRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        } else {
            throw new RuntimeException("Answer não encontrado");
        }
    }

    public Answer insert(Answer answer) {
		return AnswerRepository.save(answer);
	}

    public void deleteAnswer(Long id) {
        AnswerRepository.deleteById(id);
    }

    public Answer update(Long id, Answer answer){
        Answer entity = AnswerRepository.getReferenceById(id);
        updateDaTa(entity, answer);
        return AnswerRepository.save(entity);
    }

    protected void updateDaTa(Answer entity, Answer answer) {
        entity.setAnswerText(answer.getAnswerText());
    }
}
