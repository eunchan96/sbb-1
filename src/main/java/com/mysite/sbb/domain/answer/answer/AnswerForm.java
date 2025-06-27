package com.mysite.sbb.domain.answer.answer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
    @NotBlank(message="내용은 필수항목입니다.")
    private String content;
}
