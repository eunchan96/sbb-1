package com.mysite.sbb.domain.answer.answer.entity;

import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.user.user.entity.SiteUser;
import com.mysite.sbb.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer extends BaseEntity {
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;
}
