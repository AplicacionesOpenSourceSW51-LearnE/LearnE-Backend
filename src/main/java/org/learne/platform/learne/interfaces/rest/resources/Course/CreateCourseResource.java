package org.learne.platform.learne.interfaces.rest.resources.Course;

import org.learne.platform.learne.domain.model.valueobjects.TeacherId;

public record CreateCourseResource(Long Id, String title, String description,
                                   TeacherId teacherId, String level, String duration,
                                   String prior_knowledge, String principal_image, String status) {
    public CreateCourseResource {
        if (Id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank.");
        }
        if (teacherId == null) {
            throw new IllegalArgumentException("Teacher id cannot be null");
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
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be blank");
        }
    }
}
