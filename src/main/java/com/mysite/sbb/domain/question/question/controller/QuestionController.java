package com.mysite.sbb.domain.question.question.controller;

import com.mysite.sbb.domain.answer.answer.AnswerForm;
import com.mysite.sbb.domain.question.question.QuestionForm;
import com.mysite.sbb.domain.question.question.entity.Question;
import com.mysite.sbb.domain.question.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    @Transactional(readOnly = true)
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    @Transactional(readOnly = true)
    public String detail(@PathVariable int id, AnswerForm answerForm, Model model) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    @Transactional(readOnly = true)
    public String showCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    @Transactional
    public String create(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
}
