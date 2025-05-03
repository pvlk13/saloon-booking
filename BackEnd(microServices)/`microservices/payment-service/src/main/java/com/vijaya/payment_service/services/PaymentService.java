package com.vijaya.payment_service.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.vijaya.payment_service.domain.PaymentTypes;
import com.vijaya.payment_service.modal.Payment;
import com.vijaya.payment_service.payload.dto.BookingDTO;
import com.vijaya.payment_service.payload.dto.UserDTO;
import com.vijaya.payment_service.payload.response.PaymentLinkRespose;

public interface PaymentService {
    PaymentLinkRespose createOrder(UserDTO user, BookingDTO booking, PaymentTypes paymentType) throws RazorpayException, StripeException;
    Payment getPaymentOrderById(Long id);
    Payment getPaymentOrderByPaymentId(String paymentId);
    PaymentLink createRazorPayPaymentLik(UserDTO user, Long amount, Long orderId) throws RazorpayException;
    String createStripePayment(UserDTO user, Long amount, Long orderId) throws StripeException;
}
