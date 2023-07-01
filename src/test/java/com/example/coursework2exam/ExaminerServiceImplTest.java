package com.example.coursework2exam;

import com.example.coursework2exam.exception.IncorrectAmountOfQuestion;
import com.example.coursework2exam.service.ExaminerServiceImpl;
import com.example.coursework2exam.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final List<Question> q = List.of(
        (new Question("Q1", "A1")),
        (new Question("Q2", "A2")),
        (new Question("Q3", "A3")),
        (new Question("Q4", "A4")),
        (new Question("Q5", "A5")));

    @Test
    public void getQuestionsTest() {
        Mockito.when(questionService.getRandomQuestion())
                .thenReturn(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3"),
                new Question("Q4", "A4")
                );

        Mockito.when(questionService.getAll()).thenReturn(q);

        assertThat(examinerService.getQuestions(4)).hasSize(4)
                .containsExactlyInAnyOrder(
                        new Question("Q1", "A1"),
                        new Question("Q2", "A2"),
                        new Question("Q3", "A3"),
                        new Question("Q4", "A4"));
    }

    @Test
    public void getQuestionsNegativeTest() {
        Mockito.when(questionService.getAll()).thenReturn(q);

        assertThatExceptionOfType(IncorrectAmountOfQuestion.class)
                .isThrownBy(()-> examinerService.getQuestions(q.size() + 1));
        assertThatExceptionOfType(IncorrectAmountOfQuestion.class)
                .isThrownBy(()-> examinerService.getQuestions(-1));
    }

}
