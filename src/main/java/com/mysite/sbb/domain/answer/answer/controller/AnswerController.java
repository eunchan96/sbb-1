package com.mysite.sbb.domain.answer.answer.controller;

import com.mysite.sbb.domain.answer.answer.AnswerForm;
import com.mysite.sbb.domain.answer.answer.service.AnswerService;
import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.question.question.service.QuestionService;
import com.mysite.sbb.domain.user.user.entity.SiteUser;
import com.mysite.sbb.domain.user.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PostMapping("/create/{id}")
    @Transactional
    public String createAnswer(Model model, @PathVariable int id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        Question question = questionService.getQuestion(id);
        SiteUser author = userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        answerService.create(question, answerForm.getContent(), author);
        return "redirect:/question/detail/" + id;
    }
}
