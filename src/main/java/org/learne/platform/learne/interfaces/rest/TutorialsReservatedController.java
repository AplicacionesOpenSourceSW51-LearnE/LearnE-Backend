package org.learne.platform.learne.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.learne.domain.model.aggregates.TutorialsReservated;
import org.learne.platform.learne.domain.model.commands.CreateTutorialsReservatedCommand;
import org.learne.platform.learne.domain.model.queries.TutorialsReservated.GetAllTutorialsReservatedQuery;
import org.learne.platform.learne.domain.model.queries.TutorialsReservated.GetTutorialsReservatedByIdQuery;
import org.learne.platform.learne.domain.services.TutorialsReservated.TutorialsReservatedCommandService;
import org.learne.platform.learne.domain.services.TutorialsReservated.TutorialsReservatedQueryService;
import org.learne.platform.learne.interfaces.rest.resources.TutorialsReservated.CreateTutorialsReservatedResource;
import org.learne.platform.learne.interfaces.rest.resources.TutorialsReservated.TutorialsReservatedResource;
import org.learne.platform.learne.interfaces.rest.transform.TutorialsReservated.CreateTutorialsReservatedCommandFromResourceAssembler;
import org.learne.platform.learne.interfaces.rest.transform.TutorialsReservated.TutorialsReservatedResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/tutorials_reservated", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Tutorials Reservated", description = "Tutorials Reservated API")
public class TutorialsReservatedController {
    private final TutorialsReservatedCommandService tutorialsReservatedCommandService;
    private final TutorialsReservatedQueryService tutorialsReservatedQueryService;
    public TutorialsReservatedController(TutorialsReservatedCommandService tutorialsReservatedCommandService,
                                         TutorialsReservatedQueryService tutorialsReservatedQueryService) {
        this.tutorialsReservatedCommandService = tutorialsReservatedCommandService;
        this.tutorialsReservatedQueryService = tutorialsReservatedQueryService;
    }
    @PostMapping
    @Operation(summary = "Create Tutorials Reservated", description = "Create tutorials reservated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tutorials Reservated created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Tutorials Reservated not found")
    })
    public ResponseEntity<TutorialsReservatedResource> createTutorialsReservated(@RequestBody CreateTutorialsReservatedResource resource) {
        var createTutorialsReservatedCommand = CreateTutorialsReservatedCommandFromResourceAssembler.toCommand(resource);
        var tutorialsReservatedId = tutorialsReservatedCommandService.handle(createTutorialsReservatedCommand);
        if (tutorialsReservatedId == null || tutorialsReservatedId <= 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getTutorialsReservatedById = new GetTutorialsReservatedByIdQuery(tutorialsReservatedId);
        var tutorialsReservated = tutorialsReservatedQueryService.handle(getTutorialsReservatedById);
        if (tutorialsReservated.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var tutorialsReservatedEntity = tutorialsReservated.get();
        var tutorialsReservatedResource = TutorialsReservatedResourceFromEntityAssembler.toResourceFromEntity(tutorialsReservatedEntity);
        return new ResponseEntity<>(tutorialsReservatedResource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get All Tutorials Reservated", description = "Get all tutorials reservated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tutorials Reservated found"),
            @ApiResponse(responseCode = "404", description = "Tutorials Reservated not found")
    })
    public ResponseEntity<List<TutorialsReservatedResource>> getAllTutorialsReservated() {
        var tutorialsReservated = tutorialsReservatedQueryService.handle(new GetAllTutorialsReservatedQuery());
        if (tutorialsReservated.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var tutorialsReservatedResource = tutorialsReservated.stream()
                .map(TutorialsReservatedResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tutorialsReservatedResource);
    }
}
