package com.pariksha.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pariksha.entity.exam.Question;
import com.pariksha.entity.exam.Quiz;
import com.pariksha.repository.QuestionRepository;
import com.pariksha.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Question addQuestion(Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getAllQuestions() {
		
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long id) {
		
		return this.questionRepository.findById(id).get();
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuuestion(Long id) {
		
		this.questionRepository.deleteById(id);
		
	}

	@Override
	public Question get(Long id) {
		
		return this.questionRepository.getOne(id);
	}

}
