package org.learne.platform.learne.interfaces.rest.transform;

import org.learne.platform.learne.domain.model.commands.CreatePaymentCommand;
import org.learne.platform.learne.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        var studentId = Long.parseLong(resource.studentId());
        var numberCard = Long.parseLong(resource.numberCard());
        var securityCode = Integer.parseInt(resource.securityCode());

        return new CreatePaymentCommand(
                studentId,
                resource.nameCard(),
                numberCard,
                resource.expireDate(),
                securityCode,
                resource.emailAddress()
        );
    }
}
