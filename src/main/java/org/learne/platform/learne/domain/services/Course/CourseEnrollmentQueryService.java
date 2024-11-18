package org.learne.platform.learne.domain.services.Course;

import org.learne.platform.learne.domain.model.queries.GetCourseEnrollmentByStudentIdAndCourseId;

public interface CourseEnrollmentQueryService {
    boolean handle(GetCourseEnrollmentByStudentIdAndCourseId query);
}
