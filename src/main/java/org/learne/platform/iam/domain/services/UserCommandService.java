package org.learne.platform.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.learne.platform.iam.domain.model.aggregates.User;
import org.learne.platform.iam.domain.model.commands.SignInCommand;
import org.learne.platform.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {

    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    Optional<User> handle(SignUpCommand command);


}
