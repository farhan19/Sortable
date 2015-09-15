import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.*;
import org.json.simple.parser.ParseException;


public class Main {
	public static void main(String[] args) throws ParseException{
		Parser parser = new Parser();
		System.out.println("Parsing Products Data...");
		ArrayList<Product> productList = parser.ParseProductData("products.txt");
		System.out.println("Parsing Listings Data...");
		ArrayList<Listing> listingList = parser.ParseListingData("listings.txt");
		
		FileWriter file;
		try {
			file = new FileWriter("results.txt");
			for(int i=0; i<productList.size(); i++){
				Product product = productList.get(i);
				String productName = product.getProduct_name();
				ArrayList<Listing> matchedListing = new ArrayList<Listing>();
				for(int j=0; j< listingList.size(); j++){
					Listing listing = listingList.get(j);
					if(listing.getManufacturer().equalsIgnoreCase(product.getManufacturer())){
						String title = listing.getTitle().toLowerCase();
						String[] parts = title.split(" ");
						boolean matched = true;
						if(product.getModel() != null){
							boolean isFound = false;
							for(int k=0; k< parts.length; k++){
								if(parts[k].toLowerCase().equals(product.getModel().toLowerCase())){
									isFound = true;
								}
							}
							if(!isFound) matched = false;
							
						}
						if(product.getFamily() != null){
							boolean isFound = false;
							for(int k=0; k< parts.length; k++){
								if(parts[k].toLowerCase().equals(product.getFamily().toLowerCase())){
									isFound = true;
								}
							}
							if(!isFound) matched = false;
						}
						if(matched == true){
							matchedListing.add(listing);
						}
					}
				}
				product.setListingList(matchedListing);
				JSONObject obj = parser.createJSONData(product);
				file.write(obj.toJSONString());
				file.write('\n');
			}
			file.flush();
			file.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Result Printed to Result.txt file...");
		
	}

}
