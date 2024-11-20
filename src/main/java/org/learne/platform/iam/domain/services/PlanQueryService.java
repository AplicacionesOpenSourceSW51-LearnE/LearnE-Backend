package org.learne.platform.iam.domain.services;

import org.learne.platform.iam.domain.model.entities.Plan;
import org.learne.platform.iam.domain.model.queries.GetAllPlansQuery;
import org.learne.platform.iam.domain.model.queries.GetPlanByNameQuery;

import java.util.List;
import java.util.Optional;

public interface PlanQueryService {
    List<Plan> handle(GetAllPlansQuery query);

    Optional<Plan> handle(GetPlanByNameQuery query);
}
