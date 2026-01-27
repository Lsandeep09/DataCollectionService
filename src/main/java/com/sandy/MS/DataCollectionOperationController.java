package com.sandy.MS;

import com.sandy.DTO.*;
import com.sandy.Service.IDcMgmtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dc-api")
@Tag(name = "dc-api", description = "Data Collection module microService")
public class DataCollectionOperationController {

    @Autowired
    private IDcMgmtService dcService;
    //displaying the plan names
    @GetMapping("/palnNames")
    public ResponseEntity<List<String>> displayPlanNames() {
        //use the service
        List<String> listPlanNames=dcService.showAllPlanNames();
        return new ResponseEntity<List<String>>(listPlanNames, HttpStatus.OK);
    }

    @PostMapping("/generateCaseNo/{appId}")
    public ResponseEntity<Integer> generateCaseNo(@PathVariable Integer appId) {
        //use service
        Integer caseNo = dcService.generateCaseNo(appId);
        return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);

    }

    @PutMapping("/updatePlanSelection/")
    public ResponseEntity<Integer> savePlanSelection(@RequestBody PlanSelectionInputs inputs) {
        //use service
        Integer caseNo = dcService.savePlanSelection(inputs);
        return new ResponseEntity<Integer>(caseNo,HttpStatus.OK);
    }

    @PostMapping("/saveIncome")
    public ResponseEntity<Integer> saveIncomeDetails(@RequestBody IncomeInputs income) {
        //use Service
        Integer caseNo = dcService.saveIncomeDetails(income);
        return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
    }

    @PostMapping("/saveEducation")
    public ResponseEntity<Integer> saveEducationDetails(@RequestBody EducationInputs education) {
        //use Service
        Integer caseNo = dcService.saveEducationDetails(education);
        return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
    }
    @PostMapping("/saveChilds")
    public ResponseEntity<Integer> saveChildrenDetails(@RequestBody List<ChildInputs> childs) {
        //use the service
        Integer caseNo = dcService.saveChildrenDetails(childs);
        return new ResponseEntity<Integer>(caseNo,HttpStatus.CREATED);
    }
    @GetMapping("/summary/{caseNO}")
    public ResponseEntity<DcSummaryReport> showSummaryReport(@PathVariable Integer caseNO){
        DcSummaryReport report = dcService.showDCSummary(caseNO);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

}
