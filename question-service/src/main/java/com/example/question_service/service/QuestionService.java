package com.example.question_service.service;

import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import com.example.question_service.repository.Dao;
//import com.example.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private Dao repo;

//    public List<Question> getAllQuestions() {
//        return repo.findAll();
//    }

    public ResponseEntity<List<Question>> getAllQuestions () {
        try {
            return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity <List<Question>>getQuestionsByCategory(String lang) {
        try{
            return new ResponseEntity<>(repo.findByCategory(lang),HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity <String> addQuestion(Question question) {
        try{
            repo.save(question);
            return new ResponseEntity<>("SUCCESSFULLY CREATED",HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("FAILED TO CREATED",HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity <String> delQuestion(Question question){
        public ResponseEntity <String> delQuestionById(int id){
        try{
//            repo.delete(question);
            repo.deleteById(id);
            return new ResponseEntity<>("SUCCESSFULLY DELETED",HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("FAILED TO DELETED",HttpStatus.BAD_REQUEST);
    }


//    public List<Question>getQuestionByCategory(String category, int num) {
//        try{
//            return repo.getQuestionByCategory(category,num);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String category, int num) {
        try{
            return new ResponseEntity<List<Integer>>(repo.getQuestionByCategory(category,num),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<Integer>>(new ArrayList<Integer>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer questionId : questionIds){
            Question question = repo.findById(questionId).get();
            questions.add(question);
        }
        for(Question question : questions){
//            QuestionWrapper qw = new QuestionWrapper(question.getId(), question.getQuestionTitle(),question.getOption1(), question.getOption2(),question.getOption3(),question.getOption4() );
            QuestionWrapper qw = new QuestionWrapper();
            qw.setId(question.getId());
            qw.setQuestionTitle(question.getQuestionTitle());
            qw.setOption1(question.getOption1());
            qw.setOption2(question.getOption2());
            qw.setOption3(question.getOption3());
            qw.setOption4(question.getOption4());

            questionWrappers.add(qw);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;
        for(Response response: responses){
            Question question = repo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
