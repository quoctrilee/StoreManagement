package Model;

public class OrderDetailsModel {
	private int orderdetailid;
	private int orderid;
	private int productid;
	private int quantity;
	private float unitprice;
	public OrderDetailsModel() {
		
	}
	
	public OrderDetailsModel(int orderdetailid, int orderid, int productid, int quantity, float unitprice) {

		this.orderdetailid = orderdetailid;
		this.orderid = orderid;
		this.productid = productid;
		this.quantity = quantity;
		this.unitprice = unitprice;
	}
	public int getOrderdetailid() {
		return orderdetailid;
	}
	public void setOrderdetailid(int orderdetailid) {
		this.orderdetailid = orderdetailid;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(float unitprice) {
		this.unitprice = unitprice;
	}
	

}
