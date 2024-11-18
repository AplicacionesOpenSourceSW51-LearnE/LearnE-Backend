package org.learne.platform.learne.domain.model.queries;

public record GetSectionByTitleQuery(String title) {
    public GetSectionByTitleQuery {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title not required.");
        }
    }
}
