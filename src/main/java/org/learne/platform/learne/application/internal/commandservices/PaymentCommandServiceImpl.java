package org.learne.platform.learne.application.internal.commandservices;

import org.learne.platform.learne.domain.model.aggregates.Payment;
import org.learne.platform.learne.domain.model.commands.CreatePaymentCommand;
import org.learne.platform.learne.domain.model.commands.DeletePaymentCommand;
import org.learne.platform.learne.domain.model.commands.UpdatePaymentCommand;
import org.learne.platform.learne.domain.services.Payment.PaymentCommandService;
import org.learne.platform.learne.infrastructure.persistence.jpa.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        if (paymentRepository.existsPaymentByStudentId(command.studentId())){
            throw new IllegalArgumentException("Payment for student already exists");
        }
        var payment = new Payment(command);
        paymentRepository.save(payment);
        return Optional.of(payment);
    }

    @Override
    public Optional<Payment> handle(UpdatePaymentCommand command) {
        var result = paymentRepository.findPaymentById(command.paymentId());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Payment with id " + command.paymentId() + " not found");
        }
        var paymentToUpdate = result.get();
        try {
            var updatePayment = paymentRepository.save(paymentToUpdate.updateInformation(command.studentId(), command.nameCard(), command.numberCard(), command.expireDate(), command.securityCode(), command.emailAddress()));
            return Optional.of(updatePayment);
        } catch (Exception e){
            throw new IllegalArgumentException("An error occurred while updating payment information. " + e.getMessage());
        }
    }

    @Override
    public void handle(DeletePaymentCommand command) {
        if(!paymentRepository.existsPaymentById(command.paymentId())) {
            throw new IllegalArgumentException("Payment with id " + command.paymentId() + " not found");
        }
        try {
            paymentRepository.deleteById(command.paymentId());
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred while deleting payment information. " + e.getMessage());
        }
    }
}
