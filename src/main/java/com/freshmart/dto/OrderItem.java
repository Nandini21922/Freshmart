package com.freshmart.dto;

public class OrderItem {

	private Integer oiId;
	private Integer oid;
	private Integer pid;
	private Double quantity;
	private Double totalPrice;

	public Integer getOiId() {
		return oiId;
	}

	public void setOiId(Integer oiId) {
		this.oiId = oiId;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}