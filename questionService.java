package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.model.Question;
 

@Service
public class questionService {
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);	
		}catch(Exception e) {
			e.printStackTrace();	
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);		
	}
	
	public ResponseEntity<List<Question>> getQuestionByCatagory(String catagory) {
		try {
			return new ResponseEntity<>(questionDao.findByCatagory(catagory),HttpStatus.OK);			
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);	 
	}
	
	public ResponseEntity<String> addQuestion(Question question ) {
		try {
			questionDao.save(question);
			return new ResponseEntity<>("sucess",HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return new ResponseEntity<>("ERROR",HttpStatus.BAD_REQUEST);
		 	 
	}

}
