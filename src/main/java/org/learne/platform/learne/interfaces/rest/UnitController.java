package org.learne.platform.learne.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.learne.domain.model.queries.GetUnitByCourseIdQuery;
import org.learne.platform.learne.domain.services.Unit.UnitCommandService;
import org.learne.platform.learne.domain.services.Unit.UnitQueryService;
import org.learne.platform.learne.interfaces.rest.resources.Unit.CreateUnitResource;
import org.learne.platform.learne.interfaces.rest.resources.Unit.UnitResource;
import org.learne.platform.learne.interfaces.rest.transform.Unit.CreateUnitCommandFromResourceAssembler;
import org.learne.platform.learne.interfaces.rest.transform.Unit.UnitResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/units", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Units", description = "Units API")
public class UnitController {
    private final UnitCommandService unitCommandService;
    private final UnitQueryService unitQueryService;

    public UnitController(UnitCommandService unitCommandService, UnitQueryService unitQueryService) {
        this.unitCommandService = unitCommandService;
        this.unitQueryService = unitQueryService;
    }

    @Operation(summary = "Create Unit", description = "Create unit for course content")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Unit created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Unit not found")
    })
    @PostMapping("/createUnit")
    public ResponseEntity<UnitResource> createUnit(@RequestBody CreateUnitResource resource) {
        var createUnitCommand = CreateUnitCommandFromResourceAssembler.toCommandFromResource(resource);
        var unit = unitCommandService.handle(createUnitCommand);

        if(unit.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var createUnit = unit.get();
        var unitResource = UnitResourceFromEntityAssembler.toResourceFromEntity(createUnit);
        return new ResponseEntity<>(unitResource, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve course unit information",
            description = "Retrieves a course unit information using their courseId"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Unit Method Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @GetMapping("/getUnitByCourseId/{courseId}")
    public ResponseEntity<UnitResource> getUnitByCourseId(@PathVariable Long courseId) {
        var unit = unitQueryService.handle(new GetUnitByCourseIdQuery(courseId));
        if(unit.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var unitEntity = unit.get();
        var unitResource = UnitResourceFromEntityAssembler.toResourceFromEntity(unitEntity);
        return ResponseEntity.ok(unitResource);
    }
}
