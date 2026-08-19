package com.example.quiz_service.controller;


import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.model.QuizDto;
import com.example.quiz_service.model.Response;
import com.example.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizservice;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto dto) {
        return quizservice.createQuiz(dto.getCategory(),dto.getNumQ(),dto.getTitle());
    }

    @GetMapping("getQuiz/{quizID}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int quizID) {
        return quizservice.getQuizById(quizID);
    }

    @PostMapping("submit/{quizID}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int quizID, @RequestBody List<Response> responses) {
        return quizservice.calcScore(quizID,responses);
    }
}
