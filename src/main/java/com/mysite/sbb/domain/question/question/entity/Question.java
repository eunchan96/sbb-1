package com.mysite.sbb.domain.question.question.entity;

import com.mysite.sbb.domain.answer.answer.entity.Answer;
import com.mysite.sbb.global.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

    public Answer addAnswer(String content){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(this);

        answers.add(answer);
        return answer;
    }
}
