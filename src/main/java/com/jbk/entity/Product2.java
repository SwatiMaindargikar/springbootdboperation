package com.jbk.entity;

public class Product2 {
	private Object productId;
	private Object productName;
	private Object productPrice;
	public Object getProductId() {
		return productId;
	}
	public void setProductId(Object productId) {
		this.productId = productId;
	}
	public Object getProductName() {
		return productName;
	}
	public void setProductName(Object productName) {
		this.productName = productName;
	}
	public Object getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Object productPrice) {
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "Product2 [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ "]";
	}
	
public Product2() {

}
public Product2(Object productId, Object productName, Object productPrice) {
	super();
	this.productId = productId;
	this.productName = productName;
	this.productPrice = productPrice;
}

}
