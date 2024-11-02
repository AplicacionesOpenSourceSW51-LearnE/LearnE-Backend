package org.learne.platform.learne.domain.model.queries;

/**
 * Query to get learned path item by course id and techaer id
 */
public record GetLearnePathItemByCourseIdAndTeacherIdQuery(Long courseId, Long teacherId) {
    public GetLearnePathItemByCourseIdAndTeacherIdQuery {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Course id cannot be null or less than or equal to zero");
        }
        if (teacherId == null || teacherId <= 0) {
            throw new IllegalArgumentException("Teacher id cannot be null or less than or equal to zero");
        }
    }
}
