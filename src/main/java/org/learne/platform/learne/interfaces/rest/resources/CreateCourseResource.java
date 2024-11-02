package org.learne.platform.learne.interfaces.rest.resources;

public record CreateCourseResource(String title, String description,
                                   String level, String duration, String prior_knowledge, String principal_image) {
    public CreateCourseResource {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank.");
        }
        if (level == null || level.isBlank() ) {
            throw new IllegalArgumentException("Level cannot be blank.");
        }
        if (duration == null || duration.isBlank()) {
            throw new IllegalArgumentException("Duration cannot be blank.");
        }
        if (prior_knowledge == null || prior_knowledge.isBlank()) {
            throw new IllegalArgumentException("Prior knowledge cannot be blank.");
        }
        if (principal_image == null || principal_image.isBlank()) {
            throw new IllegalArgumentException("Principal image cannot be blank");
        }
    }
}
