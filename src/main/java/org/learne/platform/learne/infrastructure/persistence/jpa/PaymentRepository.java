package org.learne.platform.learne.infrastructure.persistence.jpa;

import org.learne.platform.learne.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findPaymentByStudentId(Long studentId);
    Optional<Payment> findPaymentById(Long id);
    boolean existsPaymentById(Long id);
    boolean existsPaymentByStudentId(Long studentId);
}
