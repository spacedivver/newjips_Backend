package com.kb.loan.mapper;

import com.kb.loan.dto.LoanDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoanMapper {
    List<LoanDTO> getLoanList();
    LoanDTO getLoanDetail(@Param("lno") Long lno);
}
