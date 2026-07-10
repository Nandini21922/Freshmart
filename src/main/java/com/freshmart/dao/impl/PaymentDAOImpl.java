package com.freshmart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.freshmart.dao.PaymentDAO;
import com.freshmart.dto.Payment;
import com.freshmart.utility.Connector;

public class PaymentDAOImpl implements PaymentDAO {

	private Connection con;

	public PaymentDAOImpl() {
		this.con = Connector.requestConnection();
	}

	@Override
	public void addPayment(Payment payment) {

		String sql = "INSERT INTO payment VALUES(0,?,?,?,?,?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, payment.getPaymentMode());
			ps.setString(2, payment.getStatus());
			ps.setInt(3, payment.getOid());
			ps.setDouble(4, payment.getGst());
			ps.setDouble(5, payment.getFinalPrice());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error adding payment : " + e.getMessage());
		}
	}

	@Override
	public Payment getPaymentById(Integer paymentId) {

		Payment payment = null;

		String sql = "SELECT * FROM payment WHERE payment_id=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, paymentId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				payment = new Payment();

				payment.setPaymentId(rs.getInt("payment_id"));
				payment.setPaymentMode(rs.getString("payment_mode"));
				payment.setStatus(rs.getString("status"));
				payment.setOid(rs.getInt("oid"));
				payment.setGst(rs.getDouble("gst"));
				payment.setFinalPrice(rs.getDouble("final_price"));

			}

		} catch (SQLException e) {
			System.out.println("Error fetching payment : " + e.getMessage());
		}

		return payment;
	}

	@Override
	public Payment getPaymentByOrderId(Integer oid) {

		Payment payment = null;

		String sql = "SELECT * FROM payment WHERE oid=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, oid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				payment = new Payment();

				payment.setPaymentId(rs.getInt("payment_id"));
				payment.setPaymentMode(rs.getString("payment_mode"));
				payment.setStatus(rs.getString("status"));
				payment.setOid(rs.getInt("oid"));
				payment.setGst(rs.getDouble("gst"));
				payment.setFinalPrice(rs.getDouble("final_price"));

			}

		} catch (SQLException e) {
			System.out.println("Error fetching payment : " + e.getMessage());
		}

		return payment;
	}

	@Override
	public List<Payment> getAllPayments() {

		List<Payment> paymentList = new ArrayList<>();
		Payment payment = null;

		String sql = "SELECT * FROM payment";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				payment = new Payment();

				payment.setPaymentId(rs.getInt("payment_id"));
				payment.setPaymentMode(rs.getString("payment_mode"));
				payment.setStatus(rs.getString("status"));
				payment.setOid(rs.getInt("oid"));
				payment.setGst(rs.getDouble("gst"));
				payment.setFinalPrice(rs.getDouble("final_price"));

				paymentList.add(payment);

			}

		} catch (SQLException e) {
			System.out.println("Error fetching payments : " + e.getMessage());
		}

		return paymentList;
	}

	@Override
	public void updatePayment(Payment payment) {

		String query = "UPDATE payment SET payment_mode=?,status=?,oid=?,gst=?,final_price=? WHERE payment_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, payment.getPaymentMode());
			ps.setString(2, payment.getStatus());
			ps.setInt(3, payment.getOid());
			ps.setDouble(4, payment.getGst());
			ps.setDouble(5, payment.getFinalPrice());
			ps.setInt(6, payment.getPaymentId());

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error updating payment : " + e.getMessage());
		}
	}

	@Override
	public void deletePayment(Integer paymentId) {

		String query = "DELETE FROM payment WHERE payment_id=?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, paymentId);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error deleting payment : " + e.getMessage());
		}
	}

}