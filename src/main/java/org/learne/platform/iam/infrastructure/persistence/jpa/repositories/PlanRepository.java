package org.learne.platform.iam.infrastructure.persistence.jpa.repositories;

import org.learne.platform.iam.domain.model.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    Optional<Plan> findByName(org.learne.platform.iam.domain.model.valueobjects.Plan name);

    boolean existsByName(org.learne.platform.iam.domain.model.valueobjects.Plan name);
}
