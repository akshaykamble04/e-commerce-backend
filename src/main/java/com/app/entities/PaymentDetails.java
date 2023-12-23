package com.app.entities;

import lombok.Data;

@Data
public class PaymentDetails {
	
	private String paymentMethod;
	private String status;
	private String paymentId;
	private String razorpayPaymentLinkId;
	private String razorpayPaymentLinkReferenceId;
	private String razorpayPaymentLinkStatus;
	private String razorpaymentId;

}
