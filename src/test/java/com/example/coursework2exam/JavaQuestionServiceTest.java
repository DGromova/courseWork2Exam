package com.example.coursework2exam;

import com.example.coursework2exam.exception.QuestionAlreadyAddedException;
import com.example.coursework2exam.exception.QuestionNotFoundException;
import com.example.coursework2exam.exception.QuestionsExistException;
import com.example.coursework2exam.exception.TheSameValueException;
import com.example.coursework2exam.service.JavaQuestionService;
import com.example.coursework2exam.service.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        questionService.add("Q1", "A1");
        questionService.add("Q2", "A2");
        questionService.add("Q3", "A3");
    }

    @AfterEach
    public void afterEach() {
        new ArrayList<>(questionService.getAll()).forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        int beforeCount = questionService.getAll().size();

        Question expected = new Question("Q4", "A4");

        assertThat(questionService.add("Q4", "A4"))
                .isEqualTo(expected)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount +1);
        assertThat(questionService.find("Q4")).isEqualTo(expected);
    }

    @Test
    public void addWhenTheSameValue() {
        Question question = new Question("Q4", "Q4");

        assertThatExceptionOfType(TheSameValueException.class)
                .isThrownBy(() -> questionService.add(question));
    }

    @Test
    public void addWhenQuestionAlreadyAdded() {
        Question repeatQuestion = new Question("Q2", "A2");

        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add(repeatQuestion));
    }

    @Test
    public void removeTest() {
        int beforeCount = questionService.getAll().size();

        assertThat(questionService.remove("Q3", "A3"))
                .isNotIn(questionService.getAll());
        assertThat(questionService.getAll().size()).isEqualTo(beforeCount - 1);
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove("Q4", "A4"));
    }

    @Test
    public void findTest() {
        Question expected = new Question("Q2", "A2");

        assertThat(questionService.find("Q2")).isEqualTo(expected);
    }

    @Test
    public void findWhenNotExistTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(()-> questionService.find("Q4"));
    }

    @Test
    public void getRandomQuestion() {
        assertThat(questionService.getRandomQuestion())
                .isNotNull()
                .isIn(questionService.getAll());
    }

    @Test
    public void getRandomQuestionNegativeTest() {
        afterEach();
        assertThatExceptionOfType(QuestionsExistException.class)
                .isThrownBy(() -> questionService.getRandomQuestion());
    }

}
