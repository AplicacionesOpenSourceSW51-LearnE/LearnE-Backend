package org.learne.platform.learne.interfaces.rest.resources;

public record PaymentResource(Long id, Long studentId, String nameCard, Long numberCard, String expireDate, Integer securityCode, String emailAddress) {
}
