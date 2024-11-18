package org.learne.platform.learne.domain.model.commands;

public record CreateSectionCommand(String title, String description, String urlToVideo) {
    public CreateSectionCommand {
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
