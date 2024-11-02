package org.learne.platform.auth.interfaces.rest.resources;

public record UserResource(Long Id, String firstName, String lastName, String email, String username, String password, String userType, String membership) {
}
