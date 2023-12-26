package com.pariksha.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pariksha.entity.exam.Category;
import com.pariksha.entity.exam.Quiz;
import com.pariksha.repository.QuizRepository;
import com.pariksha.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {

		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzes() {

		return new HashSet<>(this.quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {

		return this.quizRepository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long id) {
		this.quizRepository.deleteById(id);
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		
		return this.quizRepository.findByCategory(category);
	}
	
	//get active quizzes

	@Override
	public List<Quiz> getActiveQuizzes() {
		
		return this.quizRepository.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzesOfCategory(Category category) {
		
		return this.quizRepository.findByCategoryAndActive(category, true);
	}
	
	

}
