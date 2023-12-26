package com.pariksha.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.pariksha.entity.exam.Category;
import com.pariksha.entity.exam.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizzes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long id);

	public List<Quiz> getQuizzesOfCategory(Category category);
	
	public List<Quiz> getActiveQuizzes();
	
	public List<Quiz> getActiveQuizzesOfCategory(Category category);
}
