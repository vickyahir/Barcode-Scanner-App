package com.example.model;

import com.google.gson.annotations.SerializedName;

public class BarcodeModel {

	@SerializedName("indexNumber")
	private String indexNumber;

	@SerializedName("prodNumber")
	private String prodNumber;

	@SerializedName("unicNumber")
	private String unicNumber;

	@SerializedName("qty")
	private String qty;

	@SerializedName("mrp")
	private String mrp;


	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String getProdNumber() {
		return prodNumber;
	}

	public void setProdNumber(String prodNumber) {
		this.prodNumber = prodNumber;
	}

	public String getUnicNumber() {
		return unicNumber;
	}

	public void setUnicNumber(String unicNumber) {
		this.unicNumber = unicNumber;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
}