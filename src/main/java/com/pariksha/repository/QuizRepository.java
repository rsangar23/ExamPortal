package com.pariksha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pariksha.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
