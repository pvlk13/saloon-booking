package com.vijaya.payment_service.repository;

import com.vijaya.payment_service.modal.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByPaymentId(Long paymentId);

}
