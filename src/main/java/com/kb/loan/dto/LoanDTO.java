package com.kb.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanDTO {
    private Long lno;
    private String bank;
    private String url;
    private String title;
    private String subtitle;
    private String content;
    private String period;
    private String howToRepay;
    private String loanLimit;
    private String interest;
    private String loanGuide;
    private String lan;
}
