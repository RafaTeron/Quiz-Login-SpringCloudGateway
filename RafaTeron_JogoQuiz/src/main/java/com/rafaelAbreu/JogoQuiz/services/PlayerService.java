package com.rafaelAbreu.JogoQuiz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelAbreu.JogoQuiz.entities.Answer;
import com.rafaelAbreu.JogoQuiz.entities.Player;
import com.rafaelAbreu.JogoQuiz.entities.Question;
import com.rafaelAbreu.JogoQuiz.exceptions.ErroScoreException;
import com.rafaelAbreu.JogoQuiz.repositories.AnswerRepository;
import com.rafaelAbreu.JogoQuiz.repositories.PlayerRepository;
import com.rafaelAbreu.JogoQuiz.repositories.QuestionRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	public Player findById(Long id) throws RuntimeException {
		Optional<Player> obj = playerRepository.findById(id);
		if (obj.isPresent()) {
			return obj.get();
		} else {
			throw new RuntimeException("Player não encontrado");
		}
	}

	public Player insert(Player player) {
		return playerRepository.save(player);
	}

	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);
	}

	public Player update(Long id, Player player) {
		Player entity = playerRepository.getReferenceById(id);
		updateDaTa(entity, player);
		return playerRepository.save(entity);
	}

	protected void updateDaTa(Player entity, Player player) {
		entity.setName(player.getName());
	}

	public void gerarQuestionParaPlayer(Long id) {
		Optional<Player> playerOptional = playerRepository.findById(id);

		if (playerOptional.isPresent()) {
			Player player = playerOptional.get();

			if (verificarPlayerQuestionVazio(player)) {

				if (limiteDeQuestionsRespondidas(player.getId())) {
					Question questionAleatoria = encontrarQuestionNaoRespondida(player);

					adicionarPerguntaAoJogador(player, questionAleatoria);

					salvarQuestionRespondida(player);
				}
			}
		}
	}

	public boolean conferirResposta(Long id, Long opcao) throws ErroScoreException {
		Optional<Player> playerOptional = playerRepository.findById(id);

		if (playerOptional.isPresent()) {
			Player player = playerOptional.get();

			Optional<Answer> answerOptional = answerRepository.findById(opcao);
			Answer answerEscolhida = answerOptional.get();

			if (answerEscolhida.getIsCorrect() == true) {
				somarScore(player);
				return true;
			}

		}
		return false;
	}

	protected void salvarQuestionRespondida(Player player) {
		if (!player.getQuestion().isEmpty()) {
			String textoQuestion = player.getQuestion().get(0).getQuestionText();

			if (player.getQuestionRespondidas() == null) {
				player.setQuestionRespondidas(new ArrayList<>());
			}

			player.getQuestionRespondidas().add(textoQuestion);
			playerRepository.save(player);
		}
	}

	public boolean limiteDeQuestionsRespondidas(Long id) {
		Optional<Player> playerOptional = playerRepository.findById(id);
		Player player = playerOptional.get();

		List<String> questionRespondidas = player.getQuestionRespondidas();


		if (questionRespondidas == null || questionRespondidas.size() < 10) {
			return true;
		}

		return false;
	}

	public void somarScore(Player player) throws ErroScoreException {
		if (player.getPointScore() < 100 ) {
			player.setPointScore(player.getPointScore() + 10);

			playerRepository.save(player);
		} else {
			throw new ErroScoreException("Pontuação maxima!");
		}

	}

	protected void adicionarPerguntaAoJogador(Player player, Question questionAleatoria) {
		List<Question> listQuestions = new ArrayList<>();
		List<Player> players = new ArrayList<>();

		players.add(player);
		questionAleatoria.setPlayers(players);
		listQuestions.add(questionAleatoria);

		player.setQuestion(listQuestions);
		questionRepository.save(questionAleatoria);
	}

	protected Question encontrarQuestionNaoRespondida(Player player) {
		Question questionAleatoria;

		do {
			questionAleatoria = questionRepository.encontrarQuestionAleatoria();
		} while (jaFoiRespondida(player, questionAleatoria));
		return questionAleatoria;
	}

	protected boolean verificarPlayerQuestionVazio(Player player) {
		if (player.getQuestion() == null || player.getQuestion().isEmpty()) {
			return true;
		} else {
			player.getQuestion().clear();
			return true;
		}
	}

	public void resetarQuizPlayer(Long id) {
		Optional<Player> playerOptional = playerRepository.findById(id);
		if (playerOptional.isPresent()) {
			Player player = playerOptional.get();

			List<String> questionRespondidas = player.getQuestionRespondidas();
			if (questionRespondidas != null && !questionRespondidas.isEmpty()) {
				questionRespondidas.clear();
			}

			if (!player.getQuestion().isEmpty() && player.getQuestion() != null) {
				player.getQuestion().clear();
			}

			if (player.getPointScore() != null) {
				player.setPointScore(0);
			}

			playerRepository.save(player);
		}
	}

	protected boolean jaFoiRespondida(Player player, Question question) {
		List<String> perguntasRespondidas = player.getQuestionRespondidas();

		if (perguntasRespondidas != null && !perguntasRespondidas.isEmpty()) {
			return perguntasRespondidas.contains(question.getQuestionText());
		}
		return false;
	}

}