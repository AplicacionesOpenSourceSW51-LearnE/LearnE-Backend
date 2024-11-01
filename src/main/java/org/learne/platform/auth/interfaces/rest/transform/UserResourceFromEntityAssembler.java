package org.learne.platform.auth.interfaces.rest.transform;

import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.auth.domain.model.valueobjects.Memberships;
import org.learne.platform.auth.domain.model.valueobjects.UserTypes;
import org.learne.platform.auth.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getUsername(), entity.getPassword(), entity.getMembership().toString(), entity.getUserType().toString());
    }
}
