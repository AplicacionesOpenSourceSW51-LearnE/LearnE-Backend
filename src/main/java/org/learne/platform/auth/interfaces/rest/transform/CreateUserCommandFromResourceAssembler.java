package org.learne.platform.auth.interfaces.rest.transform;

import org.learne.platform.auth.domain.model.commands.CreateUserCommand;
import org.learne.platform.auth.domain.model.valueobjects.Memberships;
import org.learne.platform.auth.domain.model.valueobjects.UserTypes;
import org.learne.platform.auth.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {

    public static CreateUserCommand toCommand(CreateUserResource resource) {
        var membership = Memberships.valueOf(resource.membership());
        var userType = UserTypes.valueOf(resource.userType());
        return new CreateUserCommand(resource.firstName(), resource.firstName(), resource.email(), resource.username(), resource.password(), userType, membership);
    }
}
