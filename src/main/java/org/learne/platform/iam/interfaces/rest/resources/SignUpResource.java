package org.learne.platform.iam.interfaces.rest.resources;

import org.learne.platform.iam.domain.model.valueobjects.Plan;
import org.learne.platform.iam.domain.model.valueobjects.UserType;


public record SignUpResource(String first_name, String last_name, String username, String email, String password,
                             UserType type_user, Plan type_plan) {
}