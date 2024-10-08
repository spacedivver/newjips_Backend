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

    // Fetch the list of loans
    public List<LoanDTO> getLoanList() {
        return loanMapper.getLoanList();
    }

    // Fetch loan details by ID
    public LoanDTO getLoanDetail(Long lno) {
        LoanDTO loan = loanMapper.getLoanDetail(lno);
        if (loan == null) {
            throw new RuntimeException("Loan not found with ID: " + lno);
        }
        return loan;
    }
}
