package org.learne.platform.auth.application.internal.commandservices;

import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.auth.domain.model.commands.CreateUserCommand;
import org.learne.platform.auth.domain.services.UserCommandService;
import org.learne.platform.auth.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new IllegalArgumentException("Username already exists");
        var user = new User(command);
        var createUser = userRepository.save(user);
        return Optional.of(createUser);
    }
}
