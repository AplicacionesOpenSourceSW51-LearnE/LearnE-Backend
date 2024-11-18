package org.learne.platform.learne.domain.model.queries;

public record GetCourseEnrollmentByStudentIdAndCourseId(Long student_id, Long course_id) {
    public GetCourseEnrollmentByStudentIdAndCourseId {
        if (student_id == null || student_id <= 0) {
            throw new IllegalArgumentException("Student id cannot be null or less than or equal to zero");
        }
        if (course_id == null || course_id <= 0) {
            throw new IllegalArgumentException("Course id cannot be null or less than or equal to zero");
        }
    }
}
