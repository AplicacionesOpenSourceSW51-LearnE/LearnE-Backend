package org.learne.platform.learne.domain.model.queries.TutorialsReservated;

public record GetTutorialsReservatedByIdQuery(Long id) {
    public GetTutorialsReservatedByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id cannot be null or zero or more than 0");
        }
    }
}
