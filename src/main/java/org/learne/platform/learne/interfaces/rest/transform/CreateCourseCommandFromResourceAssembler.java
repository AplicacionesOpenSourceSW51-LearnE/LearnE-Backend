package org.learne.platform.learne.interfaces.rest.transform;

import org.learne.platform.learne.domain.model.commands.CreatedCourseCommand;
import org.learne.platform.learne.domain.model.valueobjects.StatusCourse;
import org.learne.platform.learne.interfaces.rest.resources.CreateCourseResource;

public class CreateCourseCommandFromResourceAssembler {

    public static CreatedCourseCommand toCommand(CreateCourseResource resource) {
        var status = StatusCourse.valueOf(resource.status());
        return new CreatedCourseCommand(resource.Id(), resource.title(), resource.description(), resource.teacherId(),resource.level(), resource.duration(), resource.prior_knowledge(), resource.principal_image(), status);
    }
}
