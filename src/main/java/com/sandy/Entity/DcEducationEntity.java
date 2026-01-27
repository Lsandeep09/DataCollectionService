package com.sandy.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jr_ish_DC_EDUCATION")
public class DcEducationEntity {

    @Id
    @GeneratedValue()
    private Integer educationId;
    private Integer caseNo;
    @Column(length=40)
    private String highestQlfy;
    private Integer passOutYear;
}
