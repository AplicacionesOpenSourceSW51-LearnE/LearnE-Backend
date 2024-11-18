package org.learne.platform.learne.interfaces.rest.transform.Course;

import org.learne.platform.learne.domain.model.commands.CreateCourseEnrollmentCommand;
import org.learne.platform.learne.interfaces.rest.resources.Course.CreateCourseEnrollmentResource;

public class CreateCourseEnrollmentCommandFromResourceAssembler {
    public static CreateCourseEnrollmentCommand ToCommandFromResource(CreateCourseEnrollmentResource resource) {
        return new CreateCourseEnrollmentCommand(resource.student_id(), resource.course_id());
    }
}
