package org.learne.platform.learne.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.learne.domain.model.aggregates.CourseEnrollment;
import org.learne.platform.learne.domain.model.queries.GetCourseEnrollmentByStudentIdAndCourseId;
import org.learne.platform.learne.domain.services.Course.CourseEnrollmentCommandService;
import org.learne.platform.learne.domain.services.Course.CourseEnrollmentQueryService;
import org.learne.platform.learne.interfaces.rest.resources.Course.CourseEnrollmentResource;
import org.learne.platform.learne.interfaces.rest.resources.Course.CreateCourseEnrollmentResource;
import org.learne.platform.learne.interfaces.rest.transform.Course.CourseEnrollmentResourceFromEntityAssembler;
import org.learne.platform.learne.interfaces.rest.transform.Course.CreateCourseEnrollmentCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/courses-enrollment", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Courses Enrollment", description = "Registration for the course chosen by the student.")
public class CourseEnrollmentController {
    private final CourseEnrollmentQueryService courseEnrollmentQueryService;
    private final CourseEnrollmentCommandService courseEnrollmentCommandService;

    public CourseEnrollmentController(CourseEnrollmentQueryService courseEnrollmentQueryService, CourseEnrollmentCommandService courseEnrollmentCommandService) {
        this.courseEnrollmentQueryService = courseEnrollmentQueryService;
        this.courseEnrollmentCommandService = courseEnrollmentCommandService;
    }

    @Operation(
            summary = "Create a course enrollment",
            description = "Create a course enrollment using the parameters provided by the endpoint"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course enrollment created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping("/createCourseEnrollment")
    public ResponseEntity<CourseEnrollmentResource> createCourseEnrollment(@RequestBody CreateCourseEnrollmentResource resource) {
        Optional<CourseEnrollment> courseEnrollment = courseEnrollmentCommandService
                .handle(CreateCourseEnrollmentCommandFromResourceAssembler.ToCommandFromResource(resource));
        return courseEnrollment.map(source -> new ResponseEntity<>(CourseEnrollmentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Search for a specific course enrollment",
            description = "Searches for a specific course enrollment using their student id and course id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @GetMapping("/getCourseEnrollmentByStudentIdAndCourseId/{student_id, course_id}")
    public ResponseEntity<CourseEnrollmentResource> getCourseEnrollmentByStudentIdAndCourseId(@PathVariable("student_id") Long student_id, @PathVariable("course_id") Long course_id) {
        Optional<CourseEnrollment> courseEnrollment = courseEnrollmentQueryService.handle(new GetCourseEnrollmentByStudentIdAndCourseId(student_id, course_id));
        return courseEnrollment.map(source -> ResponseEntity.ok(CourseEnrollmentResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
