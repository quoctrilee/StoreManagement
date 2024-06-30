package Model;

public class ProductsModel {
	private int productid;
	private String name;
	private String description;
	private float price;
	private int quantity;
	private int categoryid;
	public ProductsModel(int productid, String name, String description, float price, int quantity, int categoryid) {
		this.productid = productid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.categoryid = categoryid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	

}
