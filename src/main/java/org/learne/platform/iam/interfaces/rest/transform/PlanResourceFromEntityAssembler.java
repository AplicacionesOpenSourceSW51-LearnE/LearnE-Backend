package org.learne.platform.iam.interfaces.rest.transform;

import org.learne.platform.iam.domain.model.entities.Plan;
import org.learne.platform.iam.interfaces.rest.resources.PlanResource;

public class PlanResourceFromEntityAssembler {
    public static PlanResource toResourceFromEntity(Plan plan) {
        return new PlanResource(plan.getId(), plan.getStringName());
    }
}
