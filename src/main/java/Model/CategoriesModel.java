package Model;

public class CategoriesModel {
	private int categoryid;
	private String name;
	public CategoriesModel(int categoryid, String name) {
		this.categoryid = categoryid;
		this.name = name;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
