package com.pariksha.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pariksha.entity.exam.Question;
import com.pariksha.entity.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	public Set<Question> findByQuiz(Quiz quiz);

}
