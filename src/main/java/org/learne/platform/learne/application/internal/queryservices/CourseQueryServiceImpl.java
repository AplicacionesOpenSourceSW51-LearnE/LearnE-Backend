package org.learne.platform.learne.application.internal.queryservices;

import org.learne.platform.learne.domain.model.aggregates.Course;
import org.learne.platform.learne.domain.model.queries.GetAllCoursesQuery;
import org.learne.platform.learne.domain.model.queries.GetCourseByTitleQuery;
import org.learne.platform.learne.domain.model.queries.GetLearnePathItemByCourseIdAndTeacherIdQuery;
import org.learne.platform.learne.domain.services.Course.CourseQueryService;
import org.learne.platform.learne.infrastructure.persistence.jpa.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseQueryServiceImpl implements CourseQueryService {
    public final CourseRepository courseRepository;

    public CourseQueryServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> handle(GetAllCoursesQuery query) {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> handle(GetCourseByTitleQuery query) {
        return courseRepository.findByTitle(query.title());
    }

    @Override
    public Optional<Course> handle(GetLearnePathItemByCourseIdAndTeacherIdQuery query) {
        return Optional.empty();
    }
}
