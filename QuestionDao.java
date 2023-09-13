package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Question;
@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
	List<Question>findByCatagory(String catagory);
    @Query(value = "SELECT * FROM question q Where q.catagory=:catagory ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
	List<Question> findRandomQuestionByCatagory(String catagory, int numQ);
 

	 

}
