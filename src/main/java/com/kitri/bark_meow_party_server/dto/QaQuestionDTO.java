package com.kitri.bark_meow_party_server.dto;

import com.kitri.bark_meow_party_server.domain.QA;
import com.kitri.bark_meow_party_server.domain.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QaQuestionDTO {
    private QA qa;
    private Question question;
}
