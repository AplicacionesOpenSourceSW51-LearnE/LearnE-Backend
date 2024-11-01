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
import org.learne.platform.auth.interfaces.rest.resources.UserResource;
import org.learne.platform.auth.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Operations related to retrieving user information for various purposes")
public class UserController {
    private final UserQueryService userQueryService;

    public UserController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
    }

    @Operation(
            summary = "Search for all users",
            description = "Searches for all created users on the platform"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "All Users found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @GetMapping("/getAllUsers")
    private ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsers = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsers);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResources);
    }

    @Operation(
            summary = "Search for a specific user",
            description = "Searches for a specific user using their username"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<UserResource> getUserByUsername(@PathVariable("username") String username) {
        Optional<User> user = userQueryService.handle(new GetUserByUsernameQuery(username));
        return user.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
