package com.sandy.Repository;

import com.sandy.Entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlanRepository extends JpaRepository<PlanEntity,Integer> {
}
