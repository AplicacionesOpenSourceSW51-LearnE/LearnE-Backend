package org.learne.platform.learne.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value object to represent the tutorial id.
 * @summary
 * This value object is used to represent the unique identifier of a teacher.
 * It throws an IllegalArgumentException if the course id is null or less than 0.
 * @see IllegalArgumentException
 * @param teacherId The teacher id.It cannot be null or less than 0.
 */
@Embeddable
public record TeacherId(Long teacherId) {

    public TeacherId {
        if(teacherId == null || teacherId < 0) {
            throw new IllegalArgumentException("Course id cannot be null or less than 0");
        }
    }
}
