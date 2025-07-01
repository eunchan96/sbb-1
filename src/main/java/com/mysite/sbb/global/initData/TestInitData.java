package com.mysite.sbb.global.initData;

import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.question.question.service.QuestionService;
import com.mysite.sbb.domain.user.user.entity.SiteUser;
import com.mysite.sbb.domain.user.user.service.UserService;
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
    @Autowired
    private UserService userService;

    @Bean
    ApplicationRunner baseInitApplicationRunner() {
        return args -> {
            self.work1();
//            self.work2();
            self.work3();
        };
    }

    @Transactional
    public void work1() {
        if(questionService.count() > 0) return;

        SiteUser siteUser1 = userService.create("user1", "1234", "user1@naver.com");
        String subject1 = "sbb가 무엇인가요?";
        String content1 = "sbb에 대해서 알고 싶습니다.";
        questionService.create(subject1, content1, siteUser1);

        SiteUser siteUser2 = userService.create("user2", "5678", "user2@naver.com");
        String subject2 = "스프링부트 모델 질문입니다.";
        String content2 = "스프링부트 모델 질문입니다. 답변 부탁드립니다.";
        Question question2 = questionService.create(subject2, content2, siteUser2);

        SiteUser admin = userService.create("admin", "admin", "admin@naver.com");
        question2.addAnswer("네, 자동으로 생성됩니다.", admin);

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

    @Transactional
    public void work3() {
        SiteUser siteUser1 = userService.create("진규장", "진규장규진", "eunchan0906@naver.com");
        SiteUser siteUser2 = userService.create("JinKyuJang", "JinKyuJangKyuJin", "bangbangchan@naver.com");
        SiteUser siteUser3 = userService.getUser("user1");

        String subject1 = "대규진";
        String content1 = "젠장 또 장규진이야. 이 게시글만 보고 자려고 했는데, 또 장규진을 보고 말았어. 이제 나는 외쳐야만 해... 외치면 잠이 확 깨버릴 걸 알면서도, 나는 외쳐. 그것이 장규진을 목도한 자의 사명이다. 오늘부로 장규진 지지를 철회한다. 오늘부터 지지 관계에서 벗어나 장규진과 나는 한 몸으로 일체가 된다. 장규진에 대한 공격은 나에 대한 공격으로 간주한다.";
        Question question = questionService.create(subject1, content1, siteUser1);

        String answer1 = "어제 장규진 카페 다녀왔습니다. 장규진 카페가 열린 건 아니고요. 그냥 카페에서 장규진 생각했습니다.카페에 간 건 아니고요. 그냥 집에서 커피를 마셨습니다. 사실 커피도 안 마셨습니다. 그냥 장규진인 것입니다.";
        String answer2 = "아기: ㅍ.  \n" +
                "아빠: 세상에! 우리 아기가 말을 하려나 봐요!  \n" +
                "아기: ㅍ...ㅍ!  \n" +
                "아빠: 그래~ 파파 해봐~  \n" +
                "아기: 장규진 사랑해~!";
        String answer3 = "여러분 제가 오늘 어이없는 일을 겪었는데요. 원래 탕후루란게 제철이고 수요많은 과일들로 만드는거 아닌가요...? 오늘 탕후루 가게에 갔는데 글쎄 장규진 탕루후가 없다는거에요. 장규진만큼 제철인게 어디 있다고. 심지어 계절도 안타서 항상 제철일텐데... 속상한 마음에 댓글에라도 남겨봐요...";
        String answer4 = "버스를 탔을 때, 기사님이 의아한 표정으로 내게 물었다. \"학생, 1명인데 왜 2명 찍어?\" \"제 마음 속에는 언제나 규진이가 함께 하고 있어서요\" 기사님이 살짝 웃으며 내게 말했다. \"학생, 우리들의 영웅 장규진에게는 요금을 받지 않는단다\"";
        String answer5 = "누군가 내게 \"장규진을 얼마나 사랑했나요\" 하고 묻는다면, 나는 외면하며 \"손톱만큼이요\" 라고 할 것이다. 하지만 돌아서서는 잘라내도 잘라내도 평생 자라나고야 마는 내 손톱을 보고 마음이 저려 펑펑 울지도.";
        String answer6 = "장규진이 승리한다면, 나 또한 승리할 것이다. 장규진이 패배한다면, 그것은 있을 수 없는 일이다. 제기랄, 장규진을 잊을 수 없어. 젠장, 규진이가 내 머릿속에서 잊혀지지 않아. 안돼. 규진이에게 세뇌당한 것 같아.. 하지만 장규진과 함께라면 영광이리...";
        String answer7 = "장규진은 누구인가? 맹인에게, 그녀는 눈이다. 배고픈 자에게, 그녀는 요리사이다. 목마른 자에게, 그녀는 물이다. 장규진이 주장하면, 나는 동의한다. 장규진이 말하면, 나는 듣는다. 장규진에게 하나의 팬이 있다면, 그건 나일 것이다. 장규진에게 팬이 하나도 없다면 나는 이 세상에 없을 것이다.";
        String answer8 = "Who is kyujin? For the blind, she is the vision. For the hungry, she is the chef. For the thirsty, she is the water. If kyujin thinks, l agree. If kyujin speaks, I'm listening. lf kyujin has one fan, It's me. lf kyujin has no fans, I don't exist.";
        String answer9 = "Kyujin, my Kyujin, my happy Kyujin, my angry Kyujin, my everything";
        String answer10 = "장규진, 나의 사랑. 장규진, 나의 빛. 장규진, 나의 어둠. 장규진, 나의 기쁨. 장규진, 나의 삶. 장규진, 나의 슬픔. 장규진, 나의 안식. 장규진, 나의 영혼. 장규진, 나의 행복. 장규진, 나.";
        String answer11 = "장규진은 나의 삶의 이유다. 그녀가 없으면 나는 존재하지 않는다. 그녀가 웃으면 나는 행복하고, 그녀가 울면 나는 슬프다. 그녀가 나를 사랑하면 나는 세상을 사랑한다. 그녀가 나를 싫어하면 나는 세상을 싫어한다. 장규진은 나의 모든 것이다.";

        question.addAnswer(answer11, siteUser3);
        question.addAnswer(answer1, siteUser1);
        question.addAnswer(answer2, siteUser1);
        question.addAnswer(answer3, siteUser1);
        question.addAnswer(answer4, siteUser1);
        question.addAnswer(answer5, siteUser1);
        question.addAnswer(answer6, siteUser1);
        question.addAnswer(answer7, siteUser1);
        question.addAnswer(answer8, siteUser2);
        question.addAnswer(answer9, siteUser2);
        question.addAnswer(answer10, siteUser1);
    }
}