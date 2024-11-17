package org.learne.platform.auth.application.internal.commandservices;

import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.auth.domain.model.commands.CreateUserCommand;
import org.learne.platform.auth.domain.model.commands.SignInCommand;
import org.learne.platform.auth.domain.services.UserCommandService;
import org.learne.platform.auth.infrastructure.persistence.jpa.repositories.UserRepository;
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

    @Override
    public Optional<User> handle(SignInCommand command){
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty())
            throw new RuntimeException("Profile not found");
        if (!command.password().matches(user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        return user;
    }
}
