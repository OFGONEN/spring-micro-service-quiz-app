package com.ofgonen.question_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofgonen.question_service.model.Question;
import com.ofgonen.question_service.model.Response;
import com.ofgonen.question_service.model.wrapper.QuestionClientWrapper;
import com.ofgonen.question_service.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("load")
    public ResponseEntity<String> loadQuestionsToDB() {
        return questionService.loadQuestionsToDB();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("category") String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int id) {
        return questionService.deleteQuestion(id);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
        @RequestParam String categoryName, @RequestParam Integer numberOfQuestions) {
        return questionService.getQuestionsForQuiz(categoryName, numberOfQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionClientWrapper>> getQuestions(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestions(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        System.out.println("QuestionController.getScore: " + environment.getProperty("local.server.port"));
        return questionService.getScore(responses);
    }
}