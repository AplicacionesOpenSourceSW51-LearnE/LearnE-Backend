package org.learne.platform.learne.interfaces.rest.resources.TutorialsCourses;

public record CreateTutorialsCoursesResource(Long course_id, Long teacher_id, String date, String hour,
                                             Boolean is_reservated, String link) {
}