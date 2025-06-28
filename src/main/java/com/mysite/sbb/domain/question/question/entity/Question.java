package com.mysite.sbb.domain.question.question.entity;

import com.mysite.sbb.domain.answer.answer.entity.Answer;
import com.mysite.sbb.domain.user.user.entity.SiteUser;
import com.mysite.sbb.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Question extends BaseEntity {
    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne
    private SiteUser author;

    public Answer addAnswer(String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(this);
        answer.setAuthor(author);

        answers.add(answer);
        return answer;
    }

    public void modify(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
