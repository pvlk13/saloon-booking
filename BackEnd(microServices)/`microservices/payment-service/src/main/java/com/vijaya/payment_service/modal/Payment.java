package com.vijaya.payment_service.modal;

import com.vijaya.payment_service.domain.PaymentStatus;
import com.vijaya.payment_service.domain.PaymentTypes;
import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long salonId;

    @Column(nullable = false)
    private Long bookingId;

    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(nullable = false)
    private PaymentTypes paymentType ;

    @Column(nullable = false)
    private String paymentLinkId;

    @Column(nullable = false)
    private Long amount;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSalonId() {
        return salonId;
    }

    public void setSalonId(Long salonId) {
        this.salonId = salonId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentTypes getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypes paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentLinkId() {
        return paymentLinkId;
    }

    public void setPaymentLinkId(String paymentLinkId) {
        this.paymentLinkId = paymentLinkId;
    }
}
