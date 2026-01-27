package com.sandy.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="jr_ish_DC_CASES")
@Data
public class DcCaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer caseId;
    private Integer caseNO;
    private Integer appId;
    private Integer planId;

}
