package org.learne.platform.iam.application.internal.queryservices;

import org.learne.platform.iam.domain.model.entities.Plan;
import org.learne.platform.iam.domain.model.queries.GetAllPlansQuery;
import org.learne.platform.iam.domain.model.queries.GetPlanByNameQuery;
import org.learne.platform.iam.domain.services.PlanQueryService;
import org.learne.platform.iam.infrastructure.persistence.jpa.repositories.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanQueryServiceImpl implements PlanQueryService {

    private final PlanRepository planRepository;

    public PlanQueryServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }


    @Override
    public List<Plan> handle(GetAllPlansQuery query) {
        return planRepository.findAll();
    }

    @Override
    public Optional<Plan> handle(GetPlanByNameQuery query) {
        return planRepository.findByName(query.name());
    }
}
