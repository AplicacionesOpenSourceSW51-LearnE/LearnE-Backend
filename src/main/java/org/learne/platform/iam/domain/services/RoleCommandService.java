package org.learne.platform.iam.domain.services;

import org.learne.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {

    void handle(SeedRolesCommand command);

}
