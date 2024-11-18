package org.learne.platform.learne.interfaces.rest.transform.TutorialsCourses;

import org.learne.platform.learne.domain.model.commands.TutorialsCourses.UpdateTutorialsCoursesCommand;
import org.learne.platform.learne.interfaces.rest.resources.TutorialsCourses.UpdateTutorialsCoursesResource;

public class UpdateTutorialsCoursesCommandFromResourceAssembler {
    public static UpdateTutorialsCoursesCommand toCommandFromResource(Long tutorialsCoursesId,
                                                                      UpdateTutorialsCoursesResource resource) {
        return new UpdateTutorialsCoursesCommand(tutorialsCoursesId, resource.courseId(),
                resource.teacherId(), resource.date(), resource.hour(), resource.isReservated(),
                resource.link());
    }
}
