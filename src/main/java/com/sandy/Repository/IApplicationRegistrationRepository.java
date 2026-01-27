package com.sandy.Repository;

import com.sandy.Entity.CitizenAppRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IApplicationRegistrationRepository extends JpaRepository<CitizenAppRegistrationEntity,Integer> {

}
