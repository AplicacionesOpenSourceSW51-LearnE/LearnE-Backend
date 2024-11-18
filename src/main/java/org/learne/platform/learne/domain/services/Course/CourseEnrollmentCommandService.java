package org.learne.platform.learne.domain.services.Course;

import org.learne.platform.learne.domain.model.aggregates.CourseEnrollment;
import org.learne.platform.learne.domain.model.commands.CreateCourseEnrollmentCommand;

import java.util.Optional;

public interface CourseEnrollmentCommandService {
    Optional<CourseEnrollment> handle(CreateCourseEnrollmentCommand command);
}
