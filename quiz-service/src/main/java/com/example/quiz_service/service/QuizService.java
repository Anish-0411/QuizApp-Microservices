package com.example.quiz_service.service;

//import com.example.quiz_service.model.Question;
//import com.example.quiz_service.model.QuestionWrapper;
////import com.example.quizapp.model.Quiz;
//import com.example.quiz_service.model.Response;
import com.example.quiz_service.feign.QuizInterface;
import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.model.Response;
import com.example.quiz_service.repository.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuizInterface quizInterface;

//    @Autowired
//    private QuestionService questionService;



    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try{
            List<Integer> questions = quizInterface.generateQuestionsForQuiz(category ,numQ).getBody();
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionsIds(questions);
            quizDao.save(quiz);

            return new ResponseEntity<>("SUCCESSFULLY CREATED QUIZ",HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("FAILED TO CREATE QUIZ",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizById(int quizID) {
        Quiz quiz = quizDao.findById(quizID).get();
        List<Integer> questionIds = quiz.getQuestionsIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface. getQuestionsFromId(questionIds);
        return questions;
//        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calcScore(int quizID, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}