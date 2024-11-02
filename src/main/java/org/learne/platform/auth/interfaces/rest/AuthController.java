package org.learne.platform.auth.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.learne.platform.auth.domain.model.aggregates.User;
import org.learne.platform.auth.domain.services.UserCommandService;
import org.learne.platform.auth.domain.services.UserQueryService;
import org.learne.platform.auth.interfaces.rest.resources.AuthUserResource;
import org.learne.platform.auth.interfaces.rest.resources.CreateUserResource;
import org.learne.platform.auth.interfaces.rest.resources.SignInResource;
import org.learne.platform.auth.interfaces.rest.resources.UserResource;
import org.learne.platform.auth.interfaces.rest.transform.AuthUserResourceFromEntityAssembler;
import org.learne.platform.auth.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import org.learne.platform.auth.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import org.learne.platform.auth.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Operations related to sign in and sign up")
public class AuthController {
    private final UserCommandService userCommandService;

    public AuthController(UserQueryService userQueryService, UserCommandService userCommandService) {
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
    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        Optional<User> user = userCommandService
                .handle(CreateUserCommandFromResourceAssembler.toCommand(resource));
        return user.map(source -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(source),CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @Operation(
            summary = "Sign In into the platform",
            description = "Signs Into the platform using the parameters provided by the endpoint"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Signed Into the Platform"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping("/sign-in")
    public ResponseEntity<AuthUserResource> signIn(@RequestBody SignInResource resource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
        var authUser = userCommandService.handle(signInCommand);
        if (authUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authUserResource = AuthUserResourceFromEntityAssembler.toResourceFromEntity(authUser.get());
        return ResponseEntity.ok(authUserResource);
    }
}
