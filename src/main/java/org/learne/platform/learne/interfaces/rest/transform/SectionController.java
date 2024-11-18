package org.learne.platform.learne.interfaces.rest.transform;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.learne.domain.model.aggregates.Section;
import org.learne.platform.learne.domain.model.queries.GetSectionByTitleQuery;
import org.learne.platform.learne.domain.services.Section.SectionCommandService;
import org.learne.platform.learne.domain.services.Section.SectionQueryService;
import org.learne.platform.learne.interfaces.rest.resources.Section.CreateSectionResource;
import org.learne.platform.learne.interfaces.rest.resources.Section.SectionResource;
import org.learne.platform.learne.interfaces.rest.transform.Section.CreateSectionCommandFromResourceAssembler;
import org.learne.platform.learne.interfaces.rest.transform.Section.SectionResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value ="/api/v1/sections", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Sections", description = "Various operations for managing sections towards the user.")
public class SectionController {
    private final SectionQueryService sectionQueryService;
    private final SectionCommandService sectionCommandService;

    public SectionController(SectionQueryService sectionQueryService, SectionCommandService sectionCommandService) {
        this.sectionQueryService = sectionQueryService;
        this.sectionCommandService = sectionCommandService;
    }

    @Operation(
            summary = "Search for a specific section",
            description = "Searches for a specific section using their title"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Section found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @GetMapping("/getSectionByIdQuery/{title}")
    public ResponseEntity<SectionResource> getSectionByIdQuery(@PathVariable("title") String title) {
        Optional<Section> section = sectionQueryService.handle(new GetSectionByTitleQuery(title));
        return section.map(source -> ResponseEntity.ok(SectionResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create a section",
            description = "Create a section using the parameters provided by the endpoint"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping("/createSection")
    public ResponseEntity<SectionResource> createSection(@RequestBody CreateSectionResource resource){
        Optional<Section> section = sectionCommandService
                .handle(CreateSectionCommandFromResourceAssembler.toCommand(resource));
        return section.map(source -> new ResponseEntity<>(SectionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
