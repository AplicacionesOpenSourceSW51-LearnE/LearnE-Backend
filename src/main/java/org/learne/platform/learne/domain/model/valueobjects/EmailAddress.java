package org.learne.platform.learne.domain.model.valueobjects;

import jakarta.validation.constraints.Email;

public record EmailAddress(@Email String email) {
    public EmailAddress() { this(null); }
}
