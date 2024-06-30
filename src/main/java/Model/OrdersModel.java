package Model;

import java.util.Date;

public class OrdersModel {
	private int orderid;
	private int customerid;
	private Date odersdate;
	private String status;

	public OrdersModel(int orderid, int customerid, Date odersdate, String status, float totalAmount) {
		super();
		this.orderid = orderid;
		this.customerid = customerid;
		this.odersdate = odersdate;
		this.status = status;
		this.totalAmount = totalAmount;
	}

	private float totalAmount;

	public OrdersModel() {

	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public Date getOdersdate() {
		return odersdate;
	}

	public void setOdersdate(Date odersdate) {
		this.odersdate = odersdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

}
