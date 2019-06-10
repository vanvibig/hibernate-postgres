package com.example.hibernatepostgres.controller;

import com.example.hibernatepostgres.exception.ResourceNotFoundException;
import com.example.hibernatepostgres.model.Answer;
import com.example.hibernatepostgres.repository.AnswerRepository;
import com.example.hibernatepostgres.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/{questionId}/answers")
    public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @PostMapping("/{questionId}/answers")
    public Answer addAnswer(@PathVariable Long questionId,
                            @Valid @RequestBody Answer answer) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    answer.setQuestion(question);
                    return answerRepository.save(answer);
                }).orElseThrow(
                        () -> new ResourceNotFoundException(String.format("Question not found with id %b", questionId))
                );
    }

    @PutMapping("/{questionId}/answers/{answerId}")
    public Answer updateAnswer(@PathVariable Long questionId,
                               @PathVariable Long answerId,
                               @Valid @RequestBody Answer answerRequest) {
        if (!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException(String.format("Question not found with id %b", questionId));
        }

        return answerRepository.findById(answerId)
                .map(answer -> {
                    answer.setText(answerRequest.getText());
                    return answerRepository.save(answer);
                }).orElseThrow(
                        () -> new ResourceNotFoundException(String.format("Answer not found with id %b", answerId))
                );
    }

    @DeleteMapping("/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId,
                                          @PathVariable Long answerId) {
        if (!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException(String.format("Question not found with id %b", questionId));
        }

        return answerRepository.findById(answerId)
                .map(answer -> {
                    answerRepository.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(
                        () -> new ResourceNotFoundException(String.format("Answer not found with id %b", answerId))
                );
    }
}
