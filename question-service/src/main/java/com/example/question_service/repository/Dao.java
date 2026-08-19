package com.example.question_service.repository;

import com.example.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Dao extends JpaRepository<Question, Integer> {

        @Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :num", nativeQuery = true)
        List<Integer> getQuestionByCategory(@Param("category") String category, @Param("num") int num);

        List<Question> findByCategory(String lang);
}

