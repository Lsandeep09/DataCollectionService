package com.sandy.Repository;

import com.sandy.Entity.DcIncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDcIncomeRepository extends JpaRepository<DcIncomeEntity,Integer> {
    public DcIncomeEntity findByCaseNo(int caseNo);
}
