package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Question;
import com.example.demo.service.questionService;

@RestController
@RequestMapping("question")

public class QuestionController {
	@Autowired
	questionService questionService;
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return  questionService.getAllQuestions(); 
	}
	@GetMapping("catagory/{catagory}")
	public ResponseEntity<List<Question>> getQuestionByCatagory(@PathVariable String catagory){
		return questionService.getQuestionByCatagory(catagory);
	}
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}

}
