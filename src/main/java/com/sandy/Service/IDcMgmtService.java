package com.sandy.Service;

import com.sandy.DTO.*;

import java.util.List;

public interface IDcMgmtService {
    public Integer generateCaseNo(Integer appId);
    public List<String> showAllPlanNames();
    public Integer savePlanSelection(PlanSelectionInputs plan);
    public Integer saveIncomeDetails(IncomeInputs income);
    public Integer saveEducationDetails(EducationInputs education);
    public Integer saveChildrenDetails(List<ChildInputs> children);
    public DcSummaryReport showDCSummary(Integer caseNo);
}
