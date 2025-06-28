package com.mysite.sbb.global.initData;

import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.question.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

//@Profile("test")
@Configuration
@RequiredArgsConstructor
public class TestInitData {
    @Autowired
    @Lazy
    private TestInitData self;

    private final QuestionService questionService;

    @Bean
    ApplicationRunner baseInitApplicationRunner() {
        return args -> {
            self.work1();
//            self.work2();
        };
    }

    @Transactional
    public void work1() {
        if(questionService.count() > 0) return;

        String subject1 = "sbb가 무엇인가요?";
        String content1 = "sbb에 대해서 알고 싶습니다.";
        questionService.create(subject1, content1, null);

        String subject2 = "스프링부트 모델 질문입니다.";
        String content2 = "스프링부트 모델 질문입니다. 답변 부탁드립니다.";
        Question question2 = questionService.create(subject2, content2, null);

        question2.addAnswer("네, 자동으로 생성됩니다.", null);

        System.out.println("기본 데이터가 초기화되었습니다.");
    }

    @Transactional
    public void work2() {
        for (int i = 1; i <= 300; i++) {
            String subject = "테스트 데이터입니다:[%03d]".formatted(i);
            String content = "내용무";
            questionService.create(subject, content, null);
        }
    }
}
