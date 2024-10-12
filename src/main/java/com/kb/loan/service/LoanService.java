package com.kb.loan.service;

import com.kb.loan.dto.LoanDTO;
import com.kb.loan.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanMapper loanMapper;

    @Autowired
    public LoanService(LoanMapper loanMapper) {
        this.loanMapper = loanMapper;
    }

    // Fetch the list of loans based on language
    public List<LoanDTO> getLoanList(String lan) {
        return loanMapper.getLoanList(lan);
    }

    // Fetch the top 3 loans based on language
    public List<LoanDTO> getTopLoans(String lan) {
        return loanMapper.getTopLoans(lan);
    }

    // Fetch loan details by ID and language
    public LoanDTO getLoanDetail(Long lno, String lan) {
        LoanDTO loan = loanMapper.getLoanDetail(lno, lan);
        if (loan == null) {
            throw new RuntimeException("Loan not found with ID: " + lno);
        }
        return loan;
    }
}
