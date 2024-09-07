package com.ofgonen.question_service.model.wrapper;

import com.ofgonen.question_service.model.Question;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionClientWrapper {
    public Integer id;
    public String questionTitle;
    public String option1;
    public String option2;
    public String option3;
    public String option4;

    public QuestionClientWrapper(Question question) {
        this.id = question.getId();
        this.questionTitle = question.getQuestionTitle();
        this.option1 = question.getOption1();
        this.option2 = question.getOption2();
        this.option3 = question.getOption3();
        this.option4 = question.getOption4();
    }
}
