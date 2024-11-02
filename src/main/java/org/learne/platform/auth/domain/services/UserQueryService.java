package org.learne.platform.auth.domain.services;

import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.auth.domain.model.queries.GetAllUsersQuery;
import org.learne.platform.auth.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);

    Optional<User> handle(GetUserByUsernameQuery query);
}
