package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz1;
import com.example.demo.model.Response;

@Service
public class QuizService {
	@Autowired
	
	
	QuizDao quizDao;
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String catagory, int numQ, String title) {
		List<Question> questions=questionDao.findRandomQuestionByCatagory(catagory,numQ);
		Quiz1 quiz=new Quiz1();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save( quiz);
		return  new ResponseEntity<>("sucess",HttpStatus.CREATED);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		 Optional<Quiz1>quiz1=quizDao.findById(id);
		 List<Question>questionsFromDB=quiz1.get().getQuestions();
		 List<QuestionWrapper>questionForUser=new ArrayList<>();
		 for(Question q: questionsFromDB) {
			 QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			 questionForUser.add(qw);
		 }
		 return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
	    Quiz1 quiz1 = quizDao.findById(id).get();
	    List<Question> questions = quiz1.getQuestions();
	    int right = 0;
	    int i = 0;
	    
	    for (Response response1 : responses) {
	        if (response1.getResponse().equals(questions.get(i).getRightAnswer())) {
	            right++;
	        }
	        i++;
	    }
	    
	    return new ResponseEntity<>(right, HttpStatus.OK);
	}
         
}
