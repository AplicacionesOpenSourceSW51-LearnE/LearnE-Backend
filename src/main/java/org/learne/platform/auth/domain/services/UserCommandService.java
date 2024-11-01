package org.learne.platform.auth.domain.services;

import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.auth.domain.model.commands.CreateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
}
