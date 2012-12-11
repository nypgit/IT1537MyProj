package staywell.entities;

public class Customer {
	private String customerAddress;
	private String customerId; 
	private String customerName; 
	private String customerTelephone;

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerTel() {
		return customerTelephone;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTelephone = customerTel;
	}
	
}
