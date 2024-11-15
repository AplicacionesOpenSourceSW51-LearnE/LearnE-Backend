package org.learne.platform.learne.application.internal.queryservices;

import org.learne.platform.learne.domain.model.aggregates.Payment;
import org.learne.platform.learne.domain.model.queries.GetPaymentByStudentIdQuery;
import org.learne.platform.learne.domain.services.Payment.PaymentQueryService;
import org.learne.platform.learne.infrastructure.persistence.jpa.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(GetPaymentByStudentIdQuery query) {
        return paymentRepository.findPaymentByStudentId(query.studentId());
    }
}
