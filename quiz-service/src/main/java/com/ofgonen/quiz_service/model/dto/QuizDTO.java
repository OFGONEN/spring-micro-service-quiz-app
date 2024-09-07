package com.ofgonen.quiz_service.model.dto;

import lombok.Data;

@Data
public class QuizDTO {
    String categoryName;
    Integer questionCount;
    String title;
}