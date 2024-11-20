package org.learne.platform.iam.interfaces.rest.transform;

import org.learne.platform.iam.domain.model.commands.SignUpCommand;
import org.learne.platform.iam.interfaces.rest.resources.SignUpResource;


public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.first_name(), resource.last_name(), resource.username(), resource.email(), resource.password(),
                resource.type_user(), resource.type_plan());
    }
}