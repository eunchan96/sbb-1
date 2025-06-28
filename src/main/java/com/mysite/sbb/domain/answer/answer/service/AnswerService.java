package com.mysite.sbb.domain.answer.answer.service;

import com.mysite.sbb.domain.answer.answer.repository.AnswerRepository;
import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.user.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author) {
        question.addAnswer(content, author);
    }
}
