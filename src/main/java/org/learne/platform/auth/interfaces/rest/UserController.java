package org.learne.platform.auth.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.auth.domain.model.queries.GetAllUsersQuery;
import org.learne.platform.auth.domain.model.queries.GetUserByUsernameQuery;
import org.learne.platform.auth.domain.services.UserCommandService;
import org.learne.platform.auth.domain.services.UserQueryService;
import org.learne.platform.auth.interfaces.rest.resources.CreateUserResource;
import org.learne.platform.auth.interfaces.rest.resources.UserResource;
import org.learne.platform.auth.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import org.learne.platform.auth.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Operations related to sign in and sign up")
public class UserController {
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UserController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @Operation(
            summary = "Create a user account",
            description = "Create a user account using the parameters provided by the endpoint"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User account created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        Optional<User> user = userCommandService
                .handle(CreateUserCommandFromResourceAssembler.toCommand(resource));
        return user.map(source -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(source),CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsers = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsers);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping("{username}")
    public ResponseEntity<UserResource> getUserByUsername(@PathVariable("username") String username) {
        Optional<User> user = userQueryService.handle(new GetUserByUsernameQuery(username));
        return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
