package org.learne.platform.auth.interfaces.rest.resources;

public record CreateUserResource(String firstName, String lastName, String email, String username, String password, String userType, String membership) {
    public CreateUserResource {
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
        if (userType == null || userType.isBlank()) {
            throw new IllegalArgumentException("userType cannot be null");
        }
        if (membership == null || membership.isBlank()) {
            throw new IllegalArgumentException("membership cannot be null");
        }
    }

}
