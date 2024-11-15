package org.learne.platform.learne.interfaces.rest.transform;

import org.learne.platform.learne.domain.model.aggregates.Payment;
import org.learne.platform.learne.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {

    public static PaymentResource toResourceFromEntity(Payment entity) {
        return new PaymentResource(
                entity.getId(),
                entity.getStudentId(),
                entity.getNameCard(),
                entity.getNumberCard(),
                entity.getExpireDate(),
                entity.getSecurityCode(),
                entity.getEmailAddress().toString()
        );
    }
}
