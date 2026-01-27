package com.sandy.Service;

import com.sandy.DTO.*;
import com.sandy.Entity.*;
import com.sandy.Repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DcMgmtServiceImpl implements IDcMgmtService{

    @Autowired
    private IDcCaseRepository caseRepo;
    @Autowired
    private IApplicationRegistrationRepository citizenAppRepo;

    @Autowired
    private IPlanRepository planRepo;
    @Autowired
    private IDcIncomeRepository incomeRepo;
    @Autowired
    private IDcEducationRepository educationRepo;
    @Autowired
    private IDcChildrenRepository childrenRepo;



//here we generate the case number while saving the case entity object
    @Override
    public Integer generateCaseNo(Integer appId) {
        //Load Citizen Data
        Optional<CitizenAppRegistrationEntity>  appCitizen=citizenAppRepo.findById(appId);
        if(appCitizen.isPresent()) {
            DcCaseEntity caseEntity=new DcCaseEntity();
            caseEntity.setAppId(appId);
            return caseRepo.save(caseEntity).getCaseNO();
        }
        return 0;
    }
// to show all the plans from the plan Entity
    @Override
    public List<String> showAllPlanNames() {
        List<PlanEntity> planList = planRepo.findAll();
        //get only plan names
        List<String> planNamesList= planList.stream().map(plan->plan.getPlanName()).toList();
        return planNamesList;
    }
// upadting the paln selction
    @Override
    public Integer savePlanSelection(PlanSelectionInputs plan) {
        //Load DcCaseEntity object
        Optional<DcCaseEntity> opt = caseRepo.findById(plan.getCaseNo());
        //optional class are used to avoid the null pointer exception
        if(opt.isPresent()) {
            DcCaseEntity caseEntity=opt.get();
            caseEntity.setPlanId(plan.getPlanId());
            //update the DcCaseEntity with plain id
            caseRepo.save(caseEntity);//update obj operation
            return caseEntity.getCaseNO();
        }
        return 0;
    }

    @Override
    public Integer saveIncomeDetails(IncomeInputs income) {
        //Convert binding obj data to Entity class obj data
        DcIncomeEntity  incomeEntity = new DcIncomeEntity();
        BeanUtils.copyProperties(income,incomeEntity);

        //save the income details
        incomeRepo.save(incomeEntity);
        //return caseNo
        return income.getCaseNo();
    }

    @Override
    public Integer saveEducationDetails(EducationInputs education) {
        //convert Binding object to entity object
        DcEducationEntity educationEntity = new DcEducationEntity();
        BeanUtils.copyProperties(education,educationEntity);
        //save the obj
        educationRepo.save(educationEntity);
        //return the caseNumber
        return education.getCaseNo();
    }

    @Override
    public Integer saveChildrenDetails(List<ChildInputs> children) {
        //convert each Binding class obj to each Entity class obj
        children.forEach(child->{
            DcChildrenEntity childEntity = new DcChildrenEntity();
            BeanUtils.copyProperties(child,childEntity);
            //save  each entity obj
            childrenRepo.save(childEntity);
        });
        //return the caseNO
        return children.get(0).getCaseNo();
    }

    @Override
    public DcSummaryReport showDCSummary(Integer caseNo) {
        //get multiple entity objs based on caseNo
        DcIncomeEntity incomeEntity = incomeRepo.findByCaseNo(caseNo);
        DcEducationEntity educationEntity = educationRepo.findByCaseNo(caseNo);
        List<DcChildrenEntity> childList = childrenRepo.findByCaseNo(caseNo);
        Optional<DcCaseEntity> optCaseEntity = caseRepo.findById(caseNo);
        //get PlanName
        String planName=null;
        Integer appId=null;

        // change the above planname and appid and call it directly from plan repo and appId
        if(optCaseEntity.isPresent()) {
            DcCaseEntity caseEntity = optCaseEntity.get();
            Integer planId=optCaseEntity.get().getPlanId();
            appId = caseEntity.getAppId();
            Optional<PlanEntity>optPlanEntity=planRepo.findById(planId);
            if(optPlanEntity.isPresent()) {
                planName=optPlanEntity.get().getPlanName();
            }
        }
        Optional<CitizenAppRegistrationEntity> optCitizenEntity = citizenAppRepo.findById(appId);
        CitizenAppRegistrationEntity citizenEntity = null;
        if(optCitizenEntity.isPresent())
            citizenEntity=optCitizenEntity.get();


        //convert Entity objs to Binding objs
        IncomeInputs income = new IncomeInputs();
        BeanUtils.copyProperties(incomeEntity, income);

        EducationInputs education = new EducationInputs();
        BeanUtils.copyProperties(educationEntity, education);

        List<ChildInputs> listChilds=new ArrayList<>();
        childList.forEach(childEntity->{
            ChildInputs child = new ChildInputs();
            BeanUtils.copyProperties(childEntity,child);
            listChilds.add(child);
        });
        CitizenAppRegistrationInputs citizen = new CitizenAppRegistrationInputs();
        BeanUtils.copyProperties(citizenEntity,citizen);


        //prepare Dc Summary Report obj
        DcSummaryReport report = new DcSummaryReport();
        report.setPlanName(planName);
        report.setIncomeDetails(income);
        report.setEducationDetails(education);
        report.setCitizenDetails(citizen);
        report.setChildrenDetails(listChilds);


        return report;
    }
}
