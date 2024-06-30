package Model;

public class SuppliersModel {
	private int suppliersid;
	private String name;
	private String address;
	private String phone;
	public SuppliersModel(int suppliersid, String name, String address, String phone) {
		super();
		this.suppliersid = suppliersid;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	public int getSuppliersid() {
		return suppliersid;
	}
	public void setSuppliersid(int suppliersid) {
		this.suppliersid = suppliersid;
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
