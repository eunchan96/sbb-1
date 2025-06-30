package com.mysite.sbb.domain.answer.answer.service;

import com.mysite.sbb.domain.answer.answer.entity.Answer;
import com.mysite.sbb.domain.answer.answer.repository.AnswerRepository;
import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.user.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer create(Question question, String content, SiteUser author) {
        return question.addAnswer(content, author);
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

    public Page<Answer> getAnswersByQuestionId(int questionId, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        PageRequest pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return answerRepository.findByQuestionId(questionId, pageable);
    }
}
