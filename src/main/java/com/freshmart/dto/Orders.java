package com.freshmart.dto;

import java.sql.Date;

public class Orders {

	private Integer oid;
	private Integer cid;
	private Date oDate;
	private Double totalPrice;

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Date getODate() {
		return oDate;
	}

	public void setODate(Date oDate) {
		this.oDate = oDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}