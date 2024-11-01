package org.learne.platform.auth.interfaces.rest;

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

    @GetMapping
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
