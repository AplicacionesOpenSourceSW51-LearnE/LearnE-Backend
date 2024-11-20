package org.learne.platform.iam.domain.model.queries;

public record GetUserByIdQuery(Long id) {

    public GetUserByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id is required");
        }
        if (id < 0) {
            throw new IllegalArgumentException("id must be a positive number");
        }
    }
}
