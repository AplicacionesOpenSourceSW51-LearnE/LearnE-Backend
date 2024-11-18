package org.learne.platform.learne.interfaces.rest.resources.Section;

public record CreateSectionResource(Long unit_id, String title, String description, String urlToVideo) {
    public CreateSectionResource {
        if (unit_id == null) {
            throw new IllegalArgumentException("Unit id cannot be null");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (urlToVideo == null || urlToVideo.isBlank()) {
            throw new IllegalArgumentException("Url to Video cannot be null");
        }
    }
}
