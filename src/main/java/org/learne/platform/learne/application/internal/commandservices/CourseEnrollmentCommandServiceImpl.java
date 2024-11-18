package org.learne.platform.learne.application.internal.commandservices;

import org.learne.platform.learne.domain.model.aggregates.CourseEnrollment;
import org.learne.platform.learne.domain.model.commands.CreateCourseEnrollmentCommand;
import org.learne.platform.learne.domain.services.Course.CourseEnrollmentCommandService;
import org.learne.platform.learne.infrastructure.persistence.jpa.CourseEnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseEnrollmentCommandServiceImpl implements CourseEnrollmentCommandService {

    private final CourseEnrollmentRepository _courseEnrollmentRepository;

    public CourseEnrollmentCommandServiceImpl(CourseEnrollmentRepository courseEnrollmentRepository) { this._courseEnrollmentRepository = courseEnrollmentRepository; }

    @Override
    public Optional<CourseEnrollment> handle(CreateCourseEnrollmentCommand command) {
        if (_courseEnrollmentRepository.findCourseEnrollmentBy(command.student_id(), command.course_id()))
            throw new IllegalArgumentException("Student id and Course id already exists.");
        var courseEnrollment = new CourseEnrollment(command);
        var createCourseEnrollment = _courseEnrollmentRepository.save(courseEnrollment);
        return Optional.of(createCourseEnrollment);
    }
}
