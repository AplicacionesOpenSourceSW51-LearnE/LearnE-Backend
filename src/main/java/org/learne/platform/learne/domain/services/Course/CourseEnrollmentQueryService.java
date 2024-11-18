package org.learne.platform.learne.domain.services.Course;

import org.learne.platform.learne.domain.model.aggregates.CourseEnrollment;
import org.learne.platform.learne.domain.model.queries.GetCourseEnrollmentByStudentIdAndCourseId;

import java.util.Optional;

public interface CourseEnrollmentQueryService {
    Optional<CourseEnrollment> handle(GetCourseEnrollmentByStudentIdAndCourseId query);
}
