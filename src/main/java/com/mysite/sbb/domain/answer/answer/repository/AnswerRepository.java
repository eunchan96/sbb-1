package com.mysite.sbb.domain.answer.answer.repository;

import com.mysite.sbb.domain.answer.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
