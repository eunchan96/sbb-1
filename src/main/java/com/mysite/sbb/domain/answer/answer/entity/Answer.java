package com.mysite.sbb.domain.answer.answer.entity;

import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.user.user.entity.SiteUser;
import com.mysite.sbb.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static java.time.LocalDateTime.now;

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

    @ManyToMany
    private Set<SiteUser> voters;

    @Column(nullable = false)
    private int voteCount = 0;

    public void modify(String content) {
        this.content = content;
        setModifyDate(now());
    }
}
