package com.ofgonen.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ofgonen.question_service.dao.QuestionDAO;
import com.ofgonen.question_service.model.Question;
import com.ofgonen.question_service.model.Response;
import com.ofgonen.question_service.model.wrapper.QuestionClientWrapper;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = questionDAO.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);

        }
        catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Question>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> loadQuestionsToDB() {

        String[] categories = { 
            "Geography", "Science", "Art", "Geography", "Science", "Art", "Geography", "Science", "Art", 
            "Geography", "Science", "Art", "Geography", "Science", "Art", "Geography", "Science", "Art", 
            "Geography", "Science", "Art", "Geography", "Science", "Art", "Geography", "Science", "Art", 
            "Geography", "Science", "Art" 
        };
        String[] questionTitles = { 
            "What is the capital of Turkey?", "What is the largest planet in our solar system?", "Who painted the Mona Lisa?",
            "What is the capital of France?", "What is the chemical symbol for water?", "Who painted Starry Night?",
            "What is the capital of Japan?", "What is the speed of light?", "Who painted The Scream?",
            "What is the capital of Canada?", "What is the atomic number of carbon?", "Who painted The Persistence of Memory?",
            "What is the capital of Australia?", "What is the formula for calculating force?", "Who painted The Last Supper?",
            "What is the capital of Brazil?", "What is the boiling point of water?", "Who painted The Birth of Venus?",
            "What is the capital of India?", "What is the acceleration due to gravity?", "Who painted The Night Watch?",
            "What is the capital of Russia?", "What is the chemical symbol for gold?", "Who painted The Kiss?",
            "What is the capital of Italy?", "What is the atomic number of oxygen?", "Who painted The Girl with a Pearl Earring?",
            "What is the capital of Germany?", "What is the chemical symbol for silver?", "Who painted The Arnolfini Portrait?"
        };
        String[][] options = {
            { "Istanbul", "Ankara", "Izmir", "Bursa" },
            { "Mars", "Jupiter", "Saturn", "Neptune" },
            { "Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo" },
            { "Paris", "Lyon", "Marseille", "Nice" },
            { "H2O", "O2", "CO2", "N2" },
            { "Vincent van Gogh", "Claude Monet", "Edvard Munch", "Salvador Dali" },
            { "Tokyo", "Kyoto", "Osaka", "Nagoya" },
            { "299,792,458 m/s", "150,000,000 m/s", "3,000,000 m/s", "30,000 m/s" },
            { "Edvard Munch", "Pablo Picasso", "Vincent van Gogh", "Claude Monet" },
            { "Ottawa", "Toronto", "Vancouver", "Montreal" },
            { "6", "8", "12", "14" },
            { "Salvador Dali", "Pablo Picasso", "Vincent van Gogh", "Claude Monet" },
            { "Canberra", "Sydney", "Melbourne", "Brisbane" },
            { "F = ma", "E = mc^2", "P = mv", "V = IR" },
            { "Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo" },
            { "Brasilia", "Rio de Janeiro", "Sao Paulo", "Salvador" },
            { "100°C", "0°C", "50°C", "200°C" },
            { "Sandro Botticelli", "Pablo Picasso", "Vincent van Gogh", "Claude Monet" },
            { "New Delhi", "Mumbai", "Bangalore", "Chennai" },
            { "9.8 m/s^2", "1.6 m/s^2", "3.7 m/s^2", "24.8 m/s^2" },
            { "Rembrandt", "Pablo Picasso", "Vincent van Gogh", "Claude Monet" },
            { "Moscow", "Saint Petersburg", "Novosibirsk", "Yekaterinburg" },
            { "Au", "Ag", "Pb", "Fe" },
            { "Gustav Klimt", "Pablo Picasso", "Vincent van Gogh", "Claude Monet" },
            { "Rome", "Milan", "Naples", "Turin" },
            { "8", "6", "12", "14" },
            { "Johannes Vermeer", "Pablo Picasso", "Vincent van Gogh", "Claude Monet" },
            { "Berlin", "Munich", "Frankfurt", "Hamburg" },
            { "Ag", "Au", "Pb", "Fe" },
            { "Jan van Eyck", "Pablo Picasso", "Vincent van Gogh", "Claude Monet" }
        };
        String[] rightAnswers = { 
            "Ankara", "Jupiter", "Leonardo da Vinci", "Paris", "H2O", "Vincent van Gogh", "Tokyo", "299,792,458 m/s", "Edvard Munch",
            "Ottawa", "6", "Salvador Dali", "Canberra", "F = ma", "Leonardo da Vinci", "Brasilia", "100°C", "Sandro Botticelli",
            "New Delhi", "9.8 m/s^2", "Rembrandt", "Moscow", "Au", "Gustav Klimt", "Rome", "8", "Johannes Vermeer", "Berlin", "Ag", "Jan van Eyck"
        };
        String[] difficultyLevels = { 
            "Easy", "Medium", "Hard", "Easy", "Medium", "Hard", "Easy", "Medium", "Hard", 
            "Easy", "Medium", "Hard", "Easy", "Medium", "Hard", "Easy", "Medium", "Hard", 
            "Easy", "Medium", "Hard", "Easy", "Medium", "Hard", "Easy", "Medium", "Hard", 
            "Easy", "Medium", "Hard" 
        };

        for (int i = 0; i < categories.length; i++) {
            Question question = new Question();
            question.setQuestionTitle(questionTitles[i]);
            question.setOption1(options[i][0]);
            question.setOption2(options[i][1]);
            question.setOption3(options[i][2]);
            question.setOption4(options[i][3]);
            question.setRightAnswer(rightAnswers[i]);
            question.setDifficultyLevel(difficultyLevels[i]);
            question.setCategory(categories[i]);

            try {
                questionDAO.save(question);
            }
            catch (Exception e) {
                return new ResponseEntity<>("Error loading questions!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>("Questions loaded successfully!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            List<Question> questions = questionDAO.findByCategory(category);
            return new ResponseEntity<>(questions, HttpStatus.OK);

        }
        catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Question>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDAO.save(question);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding question!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>("Question added successfully!", HttpStatus.CREATED);    
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            questionDAO.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting question!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>("Question deleted successfully!", HttpStatus.OK);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        try {
            questionDAO.save(question);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Error updating question!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Question updated successfully!", HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numberOfQuestions) {
        var questions = questionDAO.findRandomQuestionsByCategory(categoryName, numberOfQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionClientWrapper>> getQuestions(List<Integer> questionIds) {
        List<QuestionClientWrapper> questionClientWrappers = new ArrayList<>();

        for (Integer id : questionIds) {
            questionClientWrappers.add(new QuestionClientWrapper(questionDAO.findById(id).get()));
        }

        return new ResponseEntity<>(questionClientWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int rightAnswerCount = 0;

        for (Response response : responses) {
            Question question = questionDAO.findById(response.getId()).get();
            if (question.getRightAnswer().equals(response.getResponse())) {
                rightAnswerCount++;
            }
        }

        return new ResponseEntity<>(rightAnswerCount, HttpStatus.OK);
    }
}