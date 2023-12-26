package com.pariksha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pariksha.entity.exam.Category;
import com.pariksha.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	public List<Quiz> findByCategory(Category category);
	
	public List<Quiz> findByActive(Boolean isActive);
	
	public List<Quiz> findByCategoryAndActive(Category category, Boolean isActive);
}
