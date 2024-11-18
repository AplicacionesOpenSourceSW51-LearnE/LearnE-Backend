package org.learne.platform.learne.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.learne.domain.model.queries.TutorialsCourses.GetAllTutorialsCoursesQuery;
import org.learne.platform.learne.domain.model.queries.TutorialsCourses.GetTutorialsCoursesByIdQuery;
import org.learne.platform.learne.domain.services.TutorialsCourses.TutorialsCoursesCommandService;
import org.learne.platform.learne.domain.services.TutorialsCourses.TutorialsCoursesQueryService;
import org.learne.platform.learne.interfaces.rest.resources.TutorialsCourses.CreateTutorialsCoursesResource;
import org.learne.platform.learne.interfaces.rest.resources.TutorialsCourses.TutorialsCoursesResource;
import org.learne.platform.learne.interfaces.rest.transform.TutorialsCourses.CreateTutorialsCoursesCommandFromResourceAssembler;
import org.learne.platform.learne.interfaces.rest.transform.TutorialsCourses.TutorialsCoursesResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/tutorials_courses", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Tutorials Courses", description = "Tutorials Courses API")
public class TutorialsCoursesController {
    private final TutorialsCoursesCommandService tutorialsCoursesCommandService;
    private final TutorialsCoursesQueryService tutorialsCoursesQueryService;
    public TutorialsCoursesController(TutorialsCoursesCommandService tutorialsCoursesCommandService, TutorialsCoursesQueryService tutorialsCoursesQueryService) {
        this.tutorialsCoursesCommandService = tutorialsCoursesCommandService;
        this.tutorialsCoursesQueryService = tutorialsCoursesQueryService;
    }

    @PostMapping
    @Operation(summary = "Create Tutorials Courses", description = "Creates tutorial course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tutorial Course created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Tutorial Course not found")
    })
    public ResponseEntity<TutorialsCoursesResource> createTutorialsCourses(@RequestBody CreateTutorialsCoursesResource resource) {
        var createTutorialsCoursesCommand = CreateTutorialsCoursesCommandFromResourceAssembler.toCommand(resource);
        var tutorialsCoursesId = tutorialsCoursesCommandService.handle(createTutorialsCoursesCommand);
        if (tutorialsCoursesId == null || tutorialsCoursesId <= 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getTutorialsCoursesById = new GetTutorialsCoursesByIdQuery(tutorialsCoursesId);
        var tutorialsCourses = tutorialsCoursesQueryService.handle(getTutorialsCoursesById);
        if (tutorialsCourses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var tutorialsCoursesEntity = tutorialsCourses.get();
        var tutorialsCoursesResource = TutorialsCoursesResourceFromEntityAssembler.toResourceFromEntity(tutorialsCoursesEntity);
        return new ResponseEntity<>(tutorialsCoursesResource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get All Tutorials Courses", description = "Get all tutorials courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutorials Courses found"),
            @ApiResponse(responseCode = "404", description = "Tutorials Courses not found")
    })
    public ResponseEntity<List<TutorialsCoursesResource>> getAllTutorialsCourses() {
        var tutorialsCourses = tutorialsCoursesQueryService.handle(new GetAllTutorialsCoursesQuery());
        if (tutorialsCourses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var tutorialsCoursesResource = tutorialsCourses.stream()
                .map(TutorialsCoursesResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tutorialsCoursesResource);
    }
}