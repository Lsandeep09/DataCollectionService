package com.sandy.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DcSummaryReport {

    private EducationInputs educationDetails;
    private List<ChildInputs> childrenDetails;
    private IncomeInputs incomeDetails;
    private CitizenAppRegistrationInputs citizenDetails;
    private String planName;

}
