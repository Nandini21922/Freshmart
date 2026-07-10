package com.freshmart.dao;

import java.util.List;
import com.freshmart.dto.Payment;

public interface PaymentDAO {

	void addPayment(Payment payment);

	Payment getPaymentById(Integer paymentId);

	Payment getPaymentByOrderId(Integer oid);

	List<Payment> getAllPayments();

	void updatePayment(Payment payment);

	void deletePayment(Integer paymentId);

}