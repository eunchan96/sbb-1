package com.mysite.sbb.domain.question.question.repository;

import com.mysite.sbb.domain.answer.answer.entity.Answer;
import com.mysite.sbb.domain.answer.answer.repository.AnswerRepository;
import com.mysite.sbb.domain.question.question.entity.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class QuestionRepositoryTests {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    @DisplayName("findAll")
    void t1(){
        List<Question> questions = questionRepository.findAll();
        assertThat(questions.size()).isEqualTo(2);

        Question question = questions.get(0);
        assertThat(question.getSubject()).isEqualTo("sbb가 무엇인가요?");
    }

    @Test
    @DisplayName("findById")
    void t2(){
        Question question = questionRepository.findById(1).get();
        assertThat(question.getSubject()).isEqualTo("sbb가 무엇인가요?");
    }

    @Test
    @DisplayName("findBySubject")
    void t3() {
        Question question = questionRepository.findBySubject("sbb가 무엇인가요?").get();
        assertThat(question.getContent()).isEqualTo("sbb에 대해서 알고 싶습니다.");
    }

    @Test
    @DisplayName("findBySubjectAndContent")
    void t4() {
        Question question = questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.").get();
        assertThat(question.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("findBySubjectLike")
    void t5() {
        List<Question> questions = questionRepository.findBySubjectLike("sbb%");
        assertThat(questions.size()).isEqualTo(1);

        Question question1 = questions.get(0);
        assertThat(question1.getSubject()).isEqualTo("sbb가 무엇인가요?");
    }

    @Test
    @DisplayName("수정")
    void t6() {
        Question question = questionRepository.findById(1).get();
        question.setSubject("수정된 제목");
        questionRepository.save(question);

        Question updatedQuestion = questionRepository.findBySubject("수정된 제목").get();
        assertThat(updatedQuestion.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("삭제")
    void t7() {
        assertThat(questionRepository.count()).isEqualTo(2);

        Question question = questionRepository.findById(1).get();
        questionRepository.delete(question);

        assertThat(questionRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("답변 저장 1")
    void t8() {
        Question question = questionRepository.findById(2).get();

        Answer answer = new Answer();
        answer.setContent("네, 자동으로 생성됩니다.");
        answer.setQuestion(question);
        answer.setAuthor(null);
        answerRepository.save(answer);

        assertThat(question.getAnswers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("답변 저장 2")
    void t9() {
        Question question = questionRepository.findById(2).get();
        assertThat(question.getAnswers().size()).isEqualTo(1);

        Answer answer = question.addAnswer("네, 자동으로 생성됩니다.", null);

        assertThat(question.getAnswers().size()).isEqualTo(2);
        assertThat(answer.getId()).isEqualTo(0); // 새로 추가된 답변의 ID는 아직 저장되지 않았으므로 0이어야 함
    }

    @Test
    @DisplayName("답변 조회 1")
    void t10() {
        Answer answer = answerRepository.findById(1).get();
        assertThat(answer.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("답변 조회 2")
    void t11() {
        Question question = questionRepository.findById(2).get();
        List<Answer> answers = question.getAnswers();
        assertThat(answers.get(0).getId()).isEqualTo(1);
    }
}
