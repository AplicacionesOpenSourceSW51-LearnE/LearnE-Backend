package org.learne.platform.auth.domain.services;

import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.auth.domain.model.commands.CreateUserCommand;
import org.learne.platform.auth.domain.model.commands.SignInCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
    Optional<User> handle(SignInCommand command);
}
