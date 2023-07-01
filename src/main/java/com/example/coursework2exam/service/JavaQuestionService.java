package com.example.coursework2exam.service;

import com.example.coursework2exam.Question;
import com.example.coursework2exam.exception.QuestionAlreadyAddedException;
import com.example.coursework2exam.exception.QuestionNotFoundException;
import com.example.coursework2exam.exception.QuestionsExistException;
import com.example.coursework2exam.exception.TheSameValueException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final List<Question> repository = new ArrayList<>();

    @Override
    public Question add(String question, String answer) {
        Question questionWithAnswer = new Question(question, answer);
        return add(questionWithAnswer);
    }

    @Override
    public Question add(Question question) {
        checkTheSameValue(question.getQuestion(), question.getAnswer());
        checkReentry(question.getQuestion());

        repository.add(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question questionWithAnswer = new Question(question, answer);
        return remove(questionWithAnswer);
    }

    @Override
    public Question remove(Question question) {
        if (repository.contains(question)) {
            repository.remove(question);
        } else {
            throw new QuestionNotFoundException("Вопрос не найден!");
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableList(repository);
    }

    @Override
    public Question getRandomQuestion() {
        if(repository.isEmpty()) {
            throw new QuestionsExistException("Список пуст");
        }
        Random random = new Random();
        int index = random.nextInt(0, repository.size());
        return repository.get(index);
    }

    private void checkTheSameValue(String question, String answer) {
        if (question.equals(answer)) {
            throw new TheSameValueException("Одинаковый текст вопроса и ответа!");
        }
    }

    private void checkReentry(String question) {
        for (int i = 0; i < repository.size(); i++) {
            if (repository.get(i).getQuestion().equals(question)) {
                throw new QuestionAlreadyAddedException("Вопрос уже добавлен!");
            }
        }
    }

    @Override
    public Question find(String question) {
        for (int i = 0; i < repository.size(); i++) {
            if (repository.get(i).getQuestion().equals(question)) {
                return repository.get(i);
            }
        }
        throw new QuestionNotFoundException("Вопрос не найден");
    }

}
