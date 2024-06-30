package Model;

public class ImportDetailsModel {
	private int importdetailid;
	private int importid;
	private int productd;
	private int quantity;
	private float unitprice;
	
	public ImportDetailsModel() {
		
	}
	public ImportDetailsModel(int importdetailid, int importid, int productd, int quantity, float unitprice) {
		
		this.importdetailid = importdetailid;
		this.importid = importid;
		this.productd = productd;
		this.quantity = quantity;
		this.unitprice = unitprice;
	}
	public int getImportdetailid() {
		return importdetailid;
	}
	public void setImportdetailid(int importdetailid) {
		this.importdetailid = importdetailid;
	}
	public int getImportid() {
		return importid;
	}
	public void setImportid(int importid) {
		this.importid = importid;
	}
	public int getProductd() {
		return productd;
	}
	public void setProductd(int productd) {
		this.productd = productd;
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
