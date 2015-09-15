import java.util.ArrayList;

public class Product {
	String product_name;
	String manufacturer;
	String family;
	String model;
	ArrayList<Listing> listingList;

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public ArrayList<Listing> getListingList() {
		return listingList;
	}

	public void setListingList(ArrayList<Listing> listingList) {
		this.listingList = listingList;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAnnounced_date() {
		return announced_date;
	}

	public void setAnnounced_date(String announced_date) {
		this.announced_date = announced_date;
	}

	String announced_date;
}
