package org.learne.platform.learne.domain.services;

import org.learne.platform.learne.domain.model.aggregates.Course;
import org.learne.platform.learne.domain.model.queries.GetAllCoursesQuery;
import org.learne.platform.learne.domain.model.queries.GetCourseByIdQuery;
import org.learne.platform.learne.domain.model.queries.GetLearnePathItemByCourseIdAndTeacherIdQuery;


import java.util.List;
import java.util.Optional;

public interface CourseQueryService {
    List<Course> handle(GetAllCoursesQuery query);

    Optional<Course> handle(GetCourseByIdQuery query);

    Optional<Course> handle(GetLearnePathItemByCourseIdAndTeacherIdQuery query);
}
