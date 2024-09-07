package com.ofgonen.question_service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ofgonen.question_service.model.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numberOfQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numberOfQuestions);
}