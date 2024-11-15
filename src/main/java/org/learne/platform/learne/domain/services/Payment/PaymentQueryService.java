package org.learne.platform.learne.domain.services.Payment;

import org.learne.platform.learne.domain.model.aggregates.Payment;
import org.learne.platform.learne.domain.model.queries.GetPaymentByStudentIdQuery;

import java.util.Optional;

public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByStudentIdQuery query);
}
