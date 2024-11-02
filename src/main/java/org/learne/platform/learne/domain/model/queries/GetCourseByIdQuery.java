package org.learne.platform.learne.domain.model.queries;

/**
 * Query to get course by id
 * @param courseId course id
 */
public record GetCourseByIdQuery(Long courseId) {
    /**
     * Constructor
     * @param courseId course id
     *                 must be greater than 0
     *                 must not be null
     * @throws IllegalArgumentException if courseId is null or less than or equal to 0
     */
    public GetCourseByIdQuery{
        if(courseId == null || courseId <= 0){
            throw new IllegalArgumentException("Course id is required");
        }
    }
}
