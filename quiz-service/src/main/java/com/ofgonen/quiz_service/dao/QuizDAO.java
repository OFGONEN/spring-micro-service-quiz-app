package com.ofgonen.quiz_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofgonen.quiz_service.model.Quiz;

public interface QuizDAO extends JpaRepository<Quiz, Integer> {
} 