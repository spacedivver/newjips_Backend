package com.kb.loan.controller;

import com.kb.loan.dto.LoanDTO;
import com.kb.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // API to get the list of loans
    @GetMapping("/list")
    public List<LoanDTO> getLoanList() {
        return loanService.getLoanList();
    }

    // API to get the loan details by lno
    @GetMapping("/detail/{lno}")
    public LoanDTO getLoanDetail(@PathVariable("lno") Long lno) {
        return loanService.getLoanDetail(lno);

    }
}
