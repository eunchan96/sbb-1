package com.mysite.sbb.domain.answer.answer.controller;

import com.mysite.sbb.domain.answer.answer.service.AnswerService;
import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.question.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    @Transactional
    public String createAnswer(@PathVariable int id, @RequestParam(value = "content") String content, Model model) {
        Question question = questionService.getQuestion(id);
        answerService.create(question, content);
        model.addAttribute("question", question);
        return "redirect:/question/detail/" + id;
    }
}
