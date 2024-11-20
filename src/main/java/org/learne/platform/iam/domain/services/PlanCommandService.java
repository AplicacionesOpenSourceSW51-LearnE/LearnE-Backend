package org.learne.platform.iam.domain.services;

import org.learne.platform.iam.domain.model.commands.SeedPlansCommand;

public interface PlanCommandService {

    void handle(SeedPlansCommand command);

}
