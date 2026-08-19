package com.example.quiz_service.repository;


import com.example.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

    void getQuizById(int quizID);
}
