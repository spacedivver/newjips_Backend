package com.kb.loan.controller;

import com.kb.loan.dto.LoanDTO;
import com.kb.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan")
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend access
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // Get all loans
    @GetMapping("/list")
    public List<LoanDTO> getLoanList() {
        return loanService.getLoanList();
    }

    // Get loan by ID
    @GetMapping("/detail/{lno}")
    public LoanDTO getLoanDetail(@PathVariable("lno") Long lno) {
        return loanService.getLoanDetail(lno);
    }
}