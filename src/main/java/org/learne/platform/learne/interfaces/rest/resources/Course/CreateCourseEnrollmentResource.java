package org.learne.platform.learne.interfaces.rest.resources.Course;

public record CreateCourseEnrollmentResource(Long student_id, Long course_id) {
    public CreateCourseEnrollmentResource {
        if (student_id == null) {
            throw new IllegalArgumentException("Student id cannot be null");
        }
        if (course_id == null) {
            throw new IllegalArgumentException("Course id cannot be null");
        }
    }
}
