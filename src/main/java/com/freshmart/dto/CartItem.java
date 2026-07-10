package com.freshmart.dto;

public class CartItem {

	private Integer ciId;
	private Integer cartId;
	private Integer pid;
	private Double quantity;
	private Double totalPrice;

	public Integer getCiId() {
		return ciId;
	}

	public void setCiId(Integer ciId) {
		this.ciId = ciId;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
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