package com.pariksha.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.pariksha.entity.exam.Question;
import com.pariksha.entity.exam.Quiz;
import com.pariksha.service.QuestionService;
import com.pariksha.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	// add question
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}

	// update question
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}

	// get all questions of any quiz
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable Long qid) {
//					Quiz quiz = new Quiz();
//					quiz.setqId(qid);
//					java.util.Set<Question>  questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//					return ResponseEntity.ok(questionsOfQuiz);

		Quiz quiz = this.quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();

		List<Question> list = new ArrayList(questions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		
		list.forEach((q)->{
			q.setAnswer("");
		});
		
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	// get all questions of any quiz
		@GetMapping("/quiz/all/{qid}")
		public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable Long qid) {
//						Quiz quiz = new Quiz();
//						quiz.setqId(qid);
//						java.util.Set<Question>  questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//						return ResponseEntity.ok(questionsOfQuiz);

			Quiz quiz = this.quizService.getQuiz(qid);
			Set<Question> questions = quiz.getQuestions();
			
			return ResponseEntity.ok(questions);
		}

	// get single question
	@GetMapping("/{quesId}")
	public Question getQuestion(@PathVariable Long quesId) {
		return this.questionService.getQuestion(quesId);
	}

	// delete question
	@DeleteMapping("/{quesId}")
	public void deleteQues(@PathVariable Long quesId) {
		this.questionService.deleteQuuestion(quesId);
	}
	
	//eval quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions)
	{
		System.out.println(questions);
		
		  double marksGot = 0;
		  int correctAnswers = 0;
		  int attempted = 0;
		
		for(Question q:questions){
		Question question =	this.questionService.get(q.getQuesId());
		if(question.getAnswer().equals(q.getGivenAnswer())) {
			//correct answer
			correctAnswers++;
			
			double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
			marksGot += marksSingle;
		}
		
		if(q.getGivenAnswer()!=null) {
			attempted++;
		}
		
		}
		
		Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
		return ResponseEntity.ok(map);
	}
}
