package com.mysite.sbb.global.initData;

import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.question.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@Profile("test")
@Configuration
@RequiredArgsConstructor
public class TestInitData {
    @Autowired
    @Lazy
    private TestInitData self;

    private final QuestionRepository questionRepository;

    @Bean
    ApplicationRunner baseInitApplicationRunner() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        if(questionRepository.count() > 0) return;

        Question question1 = new Question();
        question1.setSubject("sbb가 무엇인가요?");
        question1.setContent("sbb에 대해서 알고 싶습니다.");
        questionRepository.save(question1);

        Question question2 = new Question();
        question2.setSubject("스프링부트 모델 질문입니다.");
        question2.setContent("id는 자동으로 생성되나요?");
        questionRepository.save(question2);

        question2.addAnswer("네, 자동으로 생성됩니다.");

        System.out.println("기본 데이터가 초기화되었습니다.");
    }
}
