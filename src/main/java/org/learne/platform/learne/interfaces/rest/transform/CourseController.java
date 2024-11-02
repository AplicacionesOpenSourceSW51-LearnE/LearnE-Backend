package org.learne.platform.learne.interfaces.rest.transform;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.learne.domain.model.aggregates.Course;
import org.learne.platform.learne.domain.model.queries.GetAllCoursesQuery;
import org.learne.platform.learne.domain.model.queries.GetCourseByTitleQuery;
import org.learne.platform.learne.domain.services.CourseCommandService;
import org.learne.platform.learne.domain.services.CourseQueryService;
import org.learne.platform.learne.interfaces.rest.resources.CourseResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1/courses", produces = APPLICATION_JSON_VALUE)
@Tag(name="Courses", description = "Various operations for managing courses towards the user..")
public class CourseController {
    private final CourseQueryService courseQueryService;

    public CourseController(CourseQueryService courseQueryService, CourseCommandService courseCommandService) {
        this.courseQueryService = courseQueryService;
    }

    @Operation(
            summary = "Search for all courses",
            description = "Searches for all created courses on the platform"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All Courses found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @GetMapping("/getAllCourses")
    private ResponseEntity<List<CourseResource>> getAllCourses() {
        var getAllCourses = new GetAllCoursesQuery();
        var courses = courseQueryService.handle(getAllCourses);
        var coursesResources = courses.stream().map(CourseResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(coursesResources);
    }

    @Operation(
            summary = "Search for a specific course",
            description = "Searches for a specific course using their title"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @GetMapping("/getCourseByIdQuery/{title}")
    public ResponseEntity<CourseResource> getCourseById(@PathVariable("title") String title) {
        Optional<Course> course = courseQueryService.handle(new GetCourseByTitleQuery(title));
        return course.map(source -> ResponseEntity.ok(CourseResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
