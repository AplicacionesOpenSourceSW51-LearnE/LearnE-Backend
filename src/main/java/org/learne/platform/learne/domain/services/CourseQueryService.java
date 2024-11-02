package org.learne.platform.learne.domain.services;

import org.learne.platform.learne.domain.model.aggregates.Course;
import org.learne.platform.learne.domain.model.queries.GetAllCoursesQuery;
import org.learne.platform.learne.domain.model.queries.GetCourseByIdQuery;
import org.learne.platform.learne.domain.model.queries.GetLearnePathItemByCourseIdAndTeacherIdQuery;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface CourseQueryService {
    List<Course> handle(GetAllCoursesQuery query);

    Optional<User> handle(GetCourseByIdQuery query);

    Optional<User> handle(GetLearnePathItemByCourseIdAndTeacherIdQuery query);
}
