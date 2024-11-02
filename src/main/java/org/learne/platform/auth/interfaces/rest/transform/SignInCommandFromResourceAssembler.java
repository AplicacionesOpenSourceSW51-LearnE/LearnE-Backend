package org.learne.platform.auth.interfaces.rest.transform;

import org.learne.platform.auth.domain.model.commands.SignInCommand;
import org.learne.platform.auth.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}
