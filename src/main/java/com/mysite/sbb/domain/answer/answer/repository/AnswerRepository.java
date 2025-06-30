package com.mysite.sbb.domain.answer.answer.repository;

import com.mysite.sbb.domain.answer.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Page<Answer> findByQuestionId(int questionId, Pageable pageable);
}
