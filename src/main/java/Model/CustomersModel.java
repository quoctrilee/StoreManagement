package Model;

public class CustomersModel {
	private int customerid;
	private String name;
	private String address;
	private String phone;
	public CustomersModel(int customerid, String name, String address, String phone) {
		this.customerid = customerid;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	public CustomersModel () {
		
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
