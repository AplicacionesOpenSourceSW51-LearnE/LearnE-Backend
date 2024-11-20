package org.learne.platform.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.learne.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.learne.platform.iam.application.internal.outboundservices.tokens.TokenService;
import org.learne.platform.iam.domain.model.aggregates.User;
import org.learne.platform.iam.domain.model.commands.SignInCommand;
import org.learne.platform.iam.domain.model.commands.SignUpCommand;
import org.learne.platform.iam.domain.services.UserCommandService;
import org.learne.platform.iam.infrastructure.persistence.jpa.repositories.PlanRepository;
import org.learne.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.learne.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    private final RoleRepository roleRepository;
    private final PlanRepository planRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository, PlanRepository planRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.planRepository = planRepository;
    }

    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link SignInCommand} command and returns the user and the token.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty())
            throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and returns the user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return the created user
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");

        if(roleRepository.findByName(command.typeUser()).isEmpty()) {
            throw new RuntimeException("Role does not exist");
        }

        if(planRepository.findByName(command.typePlan()).isEmpty()) {
            throw new RuntimeException("Plan does not exist");
        }

        var role = roleRepository.findByName(command.typeUser())
                .orElseThrow(() -> new RuntimeException("Role does not exist"));

        var plan = planRepository.findByName(command.typePlan())
                .orElseThrow(() -> new RuntimeException("Plan does not exist"));

//        // Obtener el rol desde el repositorio usando el valor del enum
//        var role = roleRepository.findByName(command.typeUser())
//                .orElseThrow(() -> new RuntimeException("El rol no existe"));
//
//        // Obtener el plan desde el repositorio usando el valor del enum
//        var plan = planRepository.findByName(command.typePlan())
//                .orElseThrow(() -> new RuntimeException("El plan no existe"));

//        var role = new Role(command.typeUser());
//        var plan = new Plan(command.typePlan());
        var user = new User(command, hashingService.encode(command.password()), role, plan);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }
}

