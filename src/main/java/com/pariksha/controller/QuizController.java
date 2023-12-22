package com.pariksha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pariksha.entity.exam.Quiz;
import com.pariksha.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;

	// add quiz service
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}

	// update quiz
	@PutMapping("/")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}

	// get all quizzes
	@GetMapping("/")
	public ResponseEntity<?> getAllQuizzes() {
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}

	// get quiz by id
	@GetMapping("/{qid}")
	public Quiz getQuiz(@PathVariable Long qid) {
		return this.quizService.getQuiz(qid);
	}

	// delete quiz
	@DeleteMapping("/{qid}")
	public void deleteQuiz(@PathVariable Long qid) {
		this.quizService.deleteQuiz(qid);
	}

}
