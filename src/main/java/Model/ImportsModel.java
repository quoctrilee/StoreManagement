package Model;

import java.sql.Date;

public class ImportsModel {
	private int importid;
	private int suppliersid;
	private Date importdate;
	private String status;
	private float total_price;

	public ImportsModel() {

	}

	public ImportsModel(int importid, int suppliersid, Date importdate, String status, float total_price) {
		super();
		this.importid = importid;
		this.suppliersid = suppliersid;
		this.importdate = importdate;
		this.status = status;
		this.total_price = total_price;
	}

	public int getImportid() {
		return importid;
	}

	public void setImportid(int importid) {
		this.importid = importid;
	}

	public int getSuppliersid() {
		return suppliersid;
	}

	public void setSuppliersid(int suppliersid) {
		this.suppliersid = suppliersid;
	}

	public Date getImportdate() {
		return importdate;
	}

	public void setImportdate(Date importdate) {
		this.importdate = importdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}

}
