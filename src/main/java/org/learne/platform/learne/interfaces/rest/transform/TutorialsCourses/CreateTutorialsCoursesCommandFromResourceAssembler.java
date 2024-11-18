package org.learne.platform.learne.interfaces.rest.transform.TutorialsCourses;

import org.learne.platform.learne.domain.model.commands.CreateTutorialsCoursesCommand;
import org.learne.platform.learne.interfaces.rest.resources.TutorialsCourses.CreateTutorialsCoursesResource;

public class CreateTutorialsCoursesCommandFromResourceAssembler {
    public static CreateTutorialsCoursesCommand toCommand(CreateTutorialsCoursesResource resource) {
        return new CreateTutorialsCoursesCommand(resource.course_id(), resource.teacher_id(),
                resource.date(), resource.hour(), resource.is_reservated(), resource.link());
    }
}