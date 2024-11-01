package org.learne.platform.auth.domain.model.commands;

public record SignInCommand(String username, String password) {
    public SignInCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username cannot be blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be blank");
        }
    }
}
