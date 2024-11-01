package org.learne.platform.auth.domain.model.queries;

public record GetUserByUsernameQuery(String username) {
    public GetUserByUsernameQuery {
        if (username == null) {
            throw new IllegalArgumentException("Username must not be null");
        }
    }
}
