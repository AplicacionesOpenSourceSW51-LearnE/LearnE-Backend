package org.learne.platform.learne.domain.model.commands.TutorialsCourses;


public record CreateTutorialsCoursesCommand(Long courseId, Long teacherId,
                                            String date, String hour, Boolean is_reservated,
                                            String link) {
    public CreateTutorialsCoursesCommand {
        if (courseId == null) {
            throw new IllegalArgumentException("Course Id is required");
        }
        if (teacherId == null) {
            throw new IllegalArgumentException("Teacher Id is required");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date is required");
        }
        if (hour == null) {
            throw new IllegalArgumentException("Hour is required");
        }
    }
}
