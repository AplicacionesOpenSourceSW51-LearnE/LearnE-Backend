package org.learne.platform.learne.domain.model.commands;

public record CreateCourseEnrollmentCommand(Long student_id, Long course_id) {
    public CreateCourseEnrollmentCommand {
        if (student_id == null) {
            throw new IllegalArgumentException("Student id cannot be null");
        }
        if (course_id == null) {
            throw new IllegalArgumentException("Course id cannot be null");
        }
    }
}
