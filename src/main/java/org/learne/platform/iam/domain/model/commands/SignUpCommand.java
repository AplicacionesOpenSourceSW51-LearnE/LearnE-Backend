package org.learne.platform.iam.domain.model.commands;

import org.learne.platform.iam.domain.model.valueobjects.Plan;
import org.learne.platform.iam.domain.model.valueobjects.UserType;

public record SignUpCommand(String firstName, String lastName, String username, String email, String password,
                            UserType typeUser, Plan typePlan) {

    public SignUpCommand {
        if(firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name is required. Its maximum size must be 100");
        }
        if(lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name is required. Its maximum size must be 100");
        }
        if(username == null || username.isBlank()) {
            throw new IllegalArgumentException("User name is required. Its maximum size must be 50");
        }
        if(email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if(password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password is required. Its maximum size must be 50");
        }
    }
}
