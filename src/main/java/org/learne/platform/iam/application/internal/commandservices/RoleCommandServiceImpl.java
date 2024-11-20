package org.learne.platform.iam.application.internal.commandservices;

import org.learne.platform.iam.domain.model.commands.SeedRolesCommand;
import org.learne.platform.iam.domain.model.entities.Role;
import org.learne.platform.iam.domain.model.valueobjects.UserType;
import org.learne.platform.iam.domain.services.RoleCommandService;
import org.learne.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * This method will handle the {@link SeedRolesCommand} and will create the roles if not exists
     * @param command {@link SeedRolesCommand}
     * @see SeedRolesCommand
     */
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(UserType.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(UserType.valueOf(role.name())));
            }
        } );
    }
}
