package com.sandy.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ChildInputs {
    private Integer childId;
    private Integer caseNo;
    private LocalDate childDOB;
    private Long childSSN;
}
