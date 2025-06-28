package com.mysite.sbb.domain.answer.answer.service;

import com.mysite.sbb.domain.answer.answer.entity.Answer;
import com.mysite.sbb.domain.answer.answer.repository.AnswerRepository;
import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.user.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author) {
        question.addAnswer(content, author);
    }

    public Answer getAnswer(int id) {
        return answerRepository.findById(id).get();
    }

    public void modify(Answer answer, String content) {
        answer.modify(content);
    }

    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    public void vote(Answer answer, SiteUser siteUser) {
        Set<SiteUser> voters = answer.getVoters();
        if (voters.contains(siteUser)) voters.remove(siteUser);
        else voters.add(siteUser);
    }
}
