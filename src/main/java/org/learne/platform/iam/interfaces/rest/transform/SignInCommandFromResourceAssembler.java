package org.learne.platform.iam.interfaces.rest.transform;

import org.learne.platform.iam.domain.model.commands.SignInCommand;
import org.learne.platform.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}