package com.sandy.Repository;

import com.sandy.Entity.DcEducationEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDcEducationRepository extends JpaRepository<DcEducationEntity,Integer> {
    public DcEducationEntity findByCaseNo(int caseNo);
}
