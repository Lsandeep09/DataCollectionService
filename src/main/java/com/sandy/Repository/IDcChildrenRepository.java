package com.sandy.Repository;

import com.sandy.Entity.DcChildrenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDcChildrenRepository extends JpaRepository<DcChildrenEntity,Integer> {
    public List<DcChildrenEntity>findByCaseNo(int caseNo);

}
