package com.ofgonen.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofgonen.quiz_service.model.Response;
import com.ofgonen.quiz_service.model.dto.QuizDTO;
import com.ofgonen.quiz_service.model.wrapper.QuestionClientWrapper;
import com.ofgonen.quiz_service.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        return quizService.createQuiz(quizDTO.getCategoryName(), quizDTO.getQuestionCount(), quizDTO.getTitle());
    }

    @GetMapping("getQuestions/{id}")
    public ResponseEntity<List<QuestionClientWrapper>> getQuiz(@PathVariable("id") Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> postMethodName(@PathVariable("id") int id, @RequestBody List<Response> responses) {
        return quizService.calculateQuiz(id, responses);
    }
}