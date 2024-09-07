package com.ofgonen.quiz_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofgonen.quiz_service.dao.QuizDAO;
import com.ofgonen.quiz_service.feign.QuizServiceInterface;
import com.ofgonen.quiz_service.model.Question;
import com.ofgonen.quiz_service.model.Quiz;
import com.ofgonen.quiz_service.model.Response;
import com.ofgonen.quiz_service.model.wrapper.QuestionClientWrapper;

@Service
public class QuizService {

    @Autowired
    private QuizDAO quizDAO;

    @Autowired
    QuizServiceInterface quizServiceInterface;

    public ResponseEntity<String> createQuiz(String category, int numberOfQuestions, String title) {
        List<Integer> questionIds = quizServiceInterface.getQuestionsForQuiz(category, numberOfQuestions).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizDAO.save(quiz);

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionClientWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz;    
        try {
            quiz = quizDAO.findById(id).orElseThrow();
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<QuestionClientWrapper> questions = quizServiceInterface.getQuestions(quiz.getQuestionIds()).getBody();

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateQuiz(int id, List<Response> responses) {
        Quiz quiz;    
        try {
            quiz = quizDAO.findById(id).orElseThrow();
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        var response = quizServiceInterface.getScore(responses);

        return response;
    }
}