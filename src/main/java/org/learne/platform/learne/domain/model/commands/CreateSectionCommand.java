package org.learne.platform.learne.domain.model.commands;

public record CreateSectionCommand(Long unit_id, String title, String description, String urlToVideo) {
    public CreateSectionCommand {
        if (unit_id == null) {
            throw new IllegalArgumentException("Unit id cannot be null");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank.");
        }
        if (urlToVideo == null || urlToVideo.isBlank()) {
            throw new IllegalArgumentException("Url to Video cannot be blank.");
        }
    }
}
