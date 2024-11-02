package org.learne.platform.learne.domain.model.queries;

/**
 * Query to get course by id
 * @param title course id
 */
public record GetCourseByTitleQuery(String title) {
    /**
     * Constructor
     * @param title course id
     *                 must be greater than 0
     *                 must not be null
     * @throws IllegalArgumentException if courseId is null or less than or equal to 0
     */
    public GetCourseByTitleQuery {
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title not required.");
        }
    }
}
