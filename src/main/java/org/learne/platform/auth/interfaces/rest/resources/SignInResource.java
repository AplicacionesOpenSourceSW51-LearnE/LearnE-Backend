package org.learne.platform.auth.interfaces.rest.resources;

public record SignInResource(String username, String password) {
    public SignInResource {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username cannot be blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be blank");
        }
    }
}
