package org.learne.platform.learne.interfaces.rest.transform.Payment;

import org.learne.platform.learne.domain.model.commands.UpdatePaymentCommand;
import org.learne.platform.learne.interfaces.rest.resources.Payment.UpdatePaymentResource;

public class UpdatePaymentCommandFromResourceAssembler {
    public static UpdatePaymentCommand toCommandFromResource(Long paymentId, UpdatePaymentResource resource) {
        var studentId = Long.parseLong(resource.studentId());
        var numberCard = Long.parseLong(resource.numberCard());
        var securityCode = Integer.parseInt(resource.securityCode());

        return new UpdatePaymentCommand(
                paymentId,
                studentId,
                resource.nameCard(),
                numberCard,
                resource.expireDate(),
                securityCode,
                resource.emailAddress()
        );
    }
}
