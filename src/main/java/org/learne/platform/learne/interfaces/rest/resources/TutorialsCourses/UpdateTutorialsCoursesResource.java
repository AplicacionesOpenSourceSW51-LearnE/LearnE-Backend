package org.learne.platform.learne.interfaces.rest.resources.TutorialsCourses;

public record UpdateTutorialsCoursesResource(Long courseId, Long teacherId, String date, String hour,
                                             Boolean isReservated, String link) {
    public UpdateTutorialsCoursesResource {
        if (courseId == null || courseId <= 0L) {
            throw new IllegalArgumentException("courseId cannot be null");
        }
        if (teacherId == null || teacherId <= 0L) {
            throw new IllegalArgumentException("teacherId cannot be null");
        }
        if (date == null || date.isBlank()) {
            throw new IllegalArgumentException("date cannot be blank");
        }
        if (hour == null || hour.isBlank()) {
            throw new IllegalArgumentException("hour cannot be blank");
        }
        if (isReservated == null || !isReservated) {
            throw new IllegalArgumentException("isReservated cannot be null");
        }
    }
}
