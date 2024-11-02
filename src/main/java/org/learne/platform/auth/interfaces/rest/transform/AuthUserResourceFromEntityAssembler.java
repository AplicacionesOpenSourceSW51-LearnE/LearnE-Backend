package org.learne.platform.auth.interfaces.rest.transform;

import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.auth.interfaces.rest.resources.AuthUserResource;

public class AuthUserResourceFromEntityAssembler {
    public static AuthUserResource toResourceFromEntity(User user) {
        return new AuthUserResource(user.getId(), user.getUsername(), user.getPassword());
    }
}
