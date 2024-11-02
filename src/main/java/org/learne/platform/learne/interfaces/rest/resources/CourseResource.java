package org.learne.platform.learne.interfaces.rest.resources;

import org.learne.platform.learne.domain.model.valueobjects.TeacherId;

public record CourseResource(Long Id, String title, String description, TeacherId teacherId,
                             String level, String duration, String prior_knowledge, String principal_image) {
}
