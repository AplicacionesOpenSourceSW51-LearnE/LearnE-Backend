package org.learne.platform.learne.application.internal.queryservices;

import org.learne.platform.learne.domain.model.queries.GetCourseEnrollmentByStudentIdAndCourseId;
import org.learne.platform.learne.domain.services.Course.CourseEnrollmentQueryService;
import org.learne.platform.learne.infrastructure.persistence.jpa.CourseEnrollmentRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseEnrollmentQueryServiceImpl implements CourseEnrollmentQueryService {
    private final CourseEnrollmentRepository _courseEnrollmentRepository;

    public CourseEnrollmentQueryServiceImpl(CourseEnrollmentRepository courseEnrollmentRepository) { this._courseEnrollmentRepository = courseEnrollmentRepository; }

    @Override
    public boolean handle(GetCourseEnrollmentByStudentIdAndCourseId query) {
        return _courseEnrollmentRepository.findCourseEnrollmentBy(query.student_id(), query.course_id());
    }
}
