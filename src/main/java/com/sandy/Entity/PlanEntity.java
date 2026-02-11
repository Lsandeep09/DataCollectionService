package com.sandy.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="jr_ish_PLAN_MASTER")
@Data
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer planId;
    @Column(length = 30)
    private String PlanName;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(length=50)
    private String description;
    private String activeSw;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationDate;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updationDate;
    @Column(length = 30)
    private String createdBy;
    @Column(length = 30)
    private String updatedBy;


}
