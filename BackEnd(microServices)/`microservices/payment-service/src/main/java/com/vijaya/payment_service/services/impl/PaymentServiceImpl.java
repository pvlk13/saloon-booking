package com.vijaya.payment_service.services.impl;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.billingportal.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.vijaya.payment_service.domain.PaymentStatus;
import com.vijaya.payment_service.domain.PaymentTypes;
import com.vijaya.payment_service.modal.Payment;
import com.vijaya.payment_service.payload.dto.BookingDTO;
import com.vijaya.payment_service.payload.dto.UserDTO;
import com.vijaya.payment_service.payload.response.PaymentLinkRespose;
import com.vijaya.payment_service.repository.PaymentRepository;
import com.vijaya.payment_service.services.PaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    public PaymentRepository paymentRepository;
    @Autowired
    public Payment payment;

    @Override
    public PaymentLinkRespose createOrder(UserDTO user, BookingDTO booking, PaymentTypes paymentType) throws RazorpayException, StripeException {
        Long amount = (long) booking.getTotalPrice();
        payment.setAmount(amount);
        payment.setUserId(user.getId());
        payment.setPaymentType(paymentType);
        payment.setBookingId(booking.getId());
        payment.setSalonId(booking.getSalonId());
        Payment savedPayment = paymentRepository.save(payment);
        PaymentLinkRespose paymentLinkRespose = new PaymentLinkRespose();
        if(paymentType.equals(PaymentTypes.RAZORPAY)) {
           PaymentLink paymentLink = createRazorPayPaymentLik(user, savedPayment.getAmount(), savedPayment.getId());
           String paymentUrl = paymentLink.get("short_url");
           String paymentId = paymentLink.get("id");
           paymentLinkRespose.setPayment_link_url(paymentUrl);
           paymentLinkRespose.setPayment_link_id(paymentId);
           savedPayment.setPaymentLinkId(paymentId);
           paymentRepository.save(savedPayment);
        }
        if(paymentType.equals(PaymentTypes.STRIPE)) {
            String paymentUrl = createStripePayment(user, savedPayment.getAmount(), savedPayment.getId());
            paymentLinkRespose.setPayment_link_url(paymentUrl);
        }
        return paymentLinkRespose;

    }

    @Override
    public Payment getPaymentOrderById(Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if(payment == null){
            throw new RuntimeException("Payment not found");
        }
        return payment;
    }

    @Override
    public Payment getPaymentOrderByPaymentId(String paymentId) {
        return paymentRepository.findByPaymentId(Long.parseLong(paymentId));
    }

    @Override
    public PaymentLink createRazorPayPaymentLik(UserDTO user, Long Amount, Long orderId) throws RazorpayException {
        Long amount = Amount*100;
        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_0V2u0y9Ct6e5L5", "oVh2tFbR8Y5hBcHvOqKZ7h1W");
        JSONObject paymentLinkResponse = new JSONObject();
        paymentLinkResponse.put("amount", amount);
        paymentLinkResponse.put("currency", "INR");

        JSONObject customer = new JSONObject();
        customer.put("name", user.getFullName());
        customer.put("email", user.getEmail());
        paymentLinkResponse.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("email", true);
        paymentLinkResponse.put("notify", notify);
        paymentLinkResponse.put("reminder_enable", true);
        paymentLinkResponse.put("callback_url", "https://localhost:8084/api/v1/payment/success");
        paymentLinkResponse.put("callback_method", "get");
        return razorpayClient.paymentLink.create(paymentLinkResponse);
    }

    @Override
    public String createStripePayment(UserDTO user, Long amount, Long orderId) throws StripeException {
        String stripeSecretKey;
        Stripe.apiKey = "stripeSecretKey";

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8084/api/v1/payment/success")
                .setCancelUrl("http://localhost:8084/api/v1/payment/cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                .setQuantity(1L)
                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount(amount*100)
                        .setProductData(SessionCreateParams.
                                LineItem.
                                PriceData.
                                ProductData.builder().setName("Salon Booking").build())
                        .build())
                        .build())
                        .build();

        Session session = Session.create((Map<String, Object>) params);
        return session.getUrl();

    }

}
