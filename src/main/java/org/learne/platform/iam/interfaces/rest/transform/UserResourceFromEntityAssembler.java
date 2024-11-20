package org.learne.platform.iam.interfaces.rest.transform;

import org.learne.platform.iam.domain.model.aggregates.User;
import org.learne.platform.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getFirstName(), user.getLastName(),
                user.getUsername(), user.getEmail(), user.getPassword(), user.getRole().getName(),
                user.getPlan().getName());
    }
}
