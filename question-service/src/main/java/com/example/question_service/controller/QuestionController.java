package com.example.question_service.controller;


import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import com.example.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService Qservice;
    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return Qservice.getAllQuestions();
    }

    @GetMapping("category/{lang}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String lang) {
        return Qservice.getQuestionsByCategory(lang);
    }

    @PostMapping("addQuestion")
    public ResponseEntity <String> addQuestion(@RequestBody Question question) {

        return Qservice.addQuestion(question);
    }
    @DeleteMapping("delQuestion/{id}")
    public ResponseEntity <String> delQuestionById(@PathVariable int id) {
        return Qservice.delQuestionById(id);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String category, @RequestParam int num) {
        return Qservice.generateQuestionsForQuiz(category,num);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        return Qservice.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        System.out.println(environment.getProperty("local.server.port"));
        return Qservice.getScore(responses);
    }












}
