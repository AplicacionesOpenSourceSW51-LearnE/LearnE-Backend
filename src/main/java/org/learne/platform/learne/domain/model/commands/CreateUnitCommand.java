package org.learne.platform.learne.domain.model.commands;

public record CreateUnitCommand(Long courseId, String title) {
    public CreateUnitCommand {
        if(courseId == null) {
            throw new IllegalArgumentException("Course id is required.");
        }
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required.");
        }
    }
}
