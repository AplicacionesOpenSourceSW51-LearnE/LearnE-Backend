package org.learne.platform.iam.domain.model.commands;


public record SignInCommand(String username, String password) {

    public SignInCommand {
        if(username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        if(password == null || password.isBlank() || password.length() > 50) {
            throw new IllegalArgumentException("Password is required. Its maximum size must be 50");
        }
    }
}
