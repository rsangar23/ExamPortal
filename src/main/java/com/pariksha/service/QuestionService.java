package com.pariksha.service;

import java.util.Set;

import com.pariksha.entity.exam.Question;
import com.pariksha.entity.exam.Quiz;

public interface QuestionService {

	public Question addQuestion(Question question);

	public Question updateQuestion(Question question);

	public Set<Question> getAllQuestions();

	public Question getQuestion(Long id);

	public Set<Question> getQuestionsOfQuiz(Quiz quiz);

	public void deleteQuuestion(Long id);

}
