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

    // Get all loans by language
    @GetMapping("/list")
    public List<LoanDTO> getLoanList(@RequestParam("lan") String lan) {
        return loanService.getLoanList(lan);
    }

    // Get top 3 loans by language
    @GetMapping("/top3")
    public List<LoanDTO> getTopLoans(@RequestParam("lan") String lan) {
        return loanService.getTopLoans(lan);
    }

    // Get loan by ID and language
    @GetMapping("/detail/{lno}")
    public LoanDTO getLoanDetail(@PathVariable("lno") Long lno, @RequestParam("lan") String lan) {
        return loanService.getLoanDetail(lno, lan);
    }
}
