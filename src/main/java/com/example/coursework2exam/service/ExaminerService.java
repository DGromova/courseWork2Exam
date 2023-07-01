package com.example.coursework2exam.service;

import com.example.coursework2exam.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection <Question> getQuestions(int amount);
}
