package org.learne.platform.learne.interfaces.rest.resources.Payment;

public record UpdatePaymentResource(String studentId, String nameCard, String numberCard, String expireDate, String securityCode, String emailAddress) {
    public UpdatePaymentResource {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("studentId cannot be blank");
        }
        if (nameCard == null || nameCard.isBlank()) {
            throw new IllegalArgumentException("nameCard cannot be blank");
        }
        if (numberCard == null || numberCard.isBlank()) {
            throw new IllegalArgumentException("numberCard cannot be blank");
        }
        if (expireDate == null || expireDate.isBlank()) {
            throw new IllegalArgumentException("expireDate cannot be blank");
        }
        if (securityCode == null || securityCode.isBlank()) {
            throw new IllegalArgumentException("securityCode cannot be blank");
        }
        if (emailAddress == null || emailAddress.isBlank()) {
            throw new IllegalArgumentException("emailAddress cannot be blank");
        }
    }
}
