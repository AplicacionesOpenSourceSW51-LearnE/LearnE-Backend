package org.learne.platform.auth.domain.model.commands;

import org.learne.platform.auth.domain.model.valueobjects.Memberships;
import org.learne.platform.auth.domain.model.valueobjects.UserTypes;

public record CreateUserCommand(String firstName, String lastName, String email, String username, String password, UserTypes userType, Memberships membership) {
    public CreateUserCommand {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName cannot be blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName cannot be blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be blank");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username cannot be blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be blank");
        }
        if (userType == null) {
            throw new IllegalArgumentException("userType cannot be null");
        }
        if (membership == null) {
            throw new IllegalArgumentException("membership cannot be null");
        }
    }
}
