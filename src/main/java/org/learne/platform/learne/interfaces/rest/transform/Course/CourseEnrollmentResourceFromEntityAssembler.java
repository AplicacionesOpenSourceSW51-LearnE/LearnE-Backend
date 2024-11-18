package org.learne.platform.learne.interfaces.rest.transform.Course;

import org.learne.platform.learne.domain.model.aggregates.CourseEnrollment;
import org.learne.platform.learne.interfaces.rest.resources.Course.CourseEnrollmentResource;

public class CourseEnrollmentResourceFromEntityAssembler {
    public static CourseEnrollmentResource toResourceFromEntity(CourseEnrollment entity) {
        return new CourseEnrollmentResource(entity.getStudent_id(), entity.getCourse_id());
    }
}
