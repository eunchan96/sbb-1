package com.mysite.sbb.domain.question.question.service;

import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.question.question.repository.QuestionRepository;
import com.mysite.sbb.domain.user.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return questionRepository.findAllByKeyword(kw, pageable);
    }

    public Question getQuestion(int id) {
        Question question = questionRepository.findById(id).get();
        return question;
    }

    public Question create(String subject, String content, SiteUser author) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setAuthor(author);
        questionRepository.save(question);
        return question;
    }

    public long count() {
        return questionRepository.count();
    }

    public void modify(Question question, String subject, String content) {
        question.modify(subject, content);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

    public void vote(Question question, SiteUser siteUser) {
        Set<SiteUser> voters = question.getVoters();

        if (voters.contains(siteUser)) voters.remove(siteUser);
        else voters.add(siteUser);
    }
}
