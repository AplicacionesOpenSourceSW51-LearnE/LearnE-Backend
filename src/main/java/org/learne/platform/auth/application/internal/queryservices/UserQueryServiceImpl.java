package org.learne.platform.auth.application.internal.queryservices;

import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.auth.domain.model.queries.GetAllUsersQuery;
import org.learne.platform.auth.domain.model.queries.GetUserByUsernameQuery;
import org.learne.platform.auth.domain.services.UserQueryService;
import org.learne.platform.auth.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    public final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> handle(GetAllUsersQuery query){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
