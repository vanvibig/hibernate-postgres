package com.example.hibernatepostgres.controller;

import com.example.hibernatepostgres.exception.ResourceNotFoundException;
import com.example.hibernatepostgres.model.Question;
import com.example.hibernatepostgres.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @GetMapping("/hello")
    public String hello() {
        return "World";
    }

    @Autowired
    private QuestionRepository questionRepository;

    /**
     * localhost:8080/questions?page=0&size=2&sort=createdAt,desc
     */
    @GetMapping()
    public Page<Question> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    /**
     * localhost:8080/questions/
     */
    @PostMapping()
    public Question createQuestion(@Valid @RequestBody Question question) {
        return questionRepository.save(question);
    }

    @PutMapping("/{questionId}")
    public Question udpateQuestion(@PathVariable Long questionId,
                                   @Valid @RequestBody Question questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setTitle(questionRequest.getTitle());
                    question.setDescription(questionRequest.getDescription());
                    return questionRepository.save(question);
                }).orElseThrow(
                        () -> new ResourceNotFoundException(String.format("Question not found with id %b", questionId))
                );
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(
                        () -> new ResourceNotFoundException(String.format("Question not found with id %b", questionId))
                );
    }
}
