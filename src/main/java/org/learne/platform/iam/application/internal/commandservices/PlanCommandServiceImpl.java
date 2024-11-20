package org.learne.platform.iam.application.internal.commandservices;

import org.learne.platform.iam.domain.model.commands.SeedPlansCommand;
import org.learne.platform.iam.domain.model.valueobjects.Plan;
import org.learne.platform.iam.domain.services.PlanCommandService;
import org.learne.platform.iam.infrastructure.persistence.jpa.repositories.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PlanCommandServiceImpl implements PlanCommandService {

    private final PlanRepository planRepository;

    public PlanCommandServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public void handle(SeedPlansCommand command) {
        Arrays.stream(Plan.values()).forEach(role -> {
            if(!planRepository.existsByName(role)) {
                planRepository.save(new org.learne.platform.iam.domain.model.entities.Plan(Plan.valueOf(role.name())));
            }
        } );
    }
}
