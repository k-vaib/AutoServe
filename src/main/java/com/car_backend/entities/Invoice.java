package com.car_backend.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="invoice")
@AttributeOverride(name="id", column=@Column(name="invoice_id"))
@Getter
@Setter

public class Invoice extends BaseEntity{
	
	@Column(name="basic_amount")
	private Double basicAmount;
	
	@Column(name="tax_amount")
	private Double taxAmount;
	
	@Column(name="total_amount")
	private Double totalAmount;
	
	@Column(name="payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@Column(name="razorpay_order_id")
	private String razorpayOrderId;
	
	@Column(name="razorpay_payment_id")
	private String razorpayPaymentId;
	
	@Column(name="razorpay_signature")
	private String razorpaySignature;
	
	@OneToOne
	@JoinColumn(name="job_card_id", nullable=false)
	private JobCard jobCard;
}
