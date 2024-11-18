package org.learne.platform.learne.domain.services.TutorialsCourses;

import org.learne.platform.learne.domain.model.commands.CreateTutorialsCoursesCommand;

public interface TutorialsCoursesCommandService {
    Long handle(CreateTutorialsCoursesCommand command);
}
