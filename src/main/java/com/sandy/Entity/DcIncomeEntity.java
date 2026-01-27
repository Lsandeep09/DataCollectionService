package com.sandy.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="jr_ish_DC_INCOME")
@Data
public class DcIncomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer incomeId;
    private Integer caseNo;
    private Double empIncome;
    private Double propertyIncome;

}
