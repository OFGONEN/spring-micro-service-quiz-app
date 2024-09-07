package com.ofgonen.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ofgonen.quiz_service.model.Response;
import com.ofgonen.quiz_service.model.wrapper.QuestionClientWrapper;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuizServiceInterface {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(
        @RequestParam String categoryName, @RequestParam Integer numberOfQuestions);

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionClientWrapper>> getQuestions(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}