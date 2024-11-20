package org.learne.platform.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.iam.domain.model.queries.GetAllPlansQuery;
import org.learne.platform.iam.domain.services.PlanQueryService;
import org.learne.platform.iam.interfaces.rest.resources.PlanResource;
import org.learne.platform.iam.interfaces.rest.transform.PlanResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ap/v1/plans", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Plans1", description = "Available Plans Endpoints")
public class PlansController {


    private final PlanQueryService planQueryService;

    public PlansController(PlanQueryService planQueryService) {
        this.planQueryService = planQueryService;
    }


    @GetMapping
    @Operation(summary = "Get all plans", description = "Get all the plans available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plans retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<List<PlanResource>> getAllPlans() {
        var getAllPlansQuery = new GetAllPlansQuery();
        var plans = planQueryService.handle(getAllPlansQuery);
        var planResources = plans.stream().map(PlanResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(planResources);
    }
}
