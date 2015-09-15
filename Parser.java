import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser {
	public ArrayList<Product> ParseProductData(String fileName) {
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			JSONParser jsonParser = new JSONParser();

			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				ContainerFactory containerFactory = new ContainerFactory() {
					public List creatArrayContainer() {
						return new LinkedList();
					}

					public Map createObjectContainer() {
						return new LinkedHashMap();
					}

				};
				Map json = (Map) jsonParser.parse(line, containerFactory);
				Iterator iter = json.entrySet().iterator();

				Product product = new Product();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					if (entry.getKey().equals("product_name")) {
						product.setProduct_name(entry.getValue().toString());
					} else if (entry.getKey().equals("manufacturer")) {
						product.setManufacturer(entry.getValue().toString());
					} else if (entry.getKey().equals("model")) {
						product.setModel(entry.getValue().toString());
					} else if (entry.getKey().equals("announced-date")) {
						product.setAnnounced_date(entry.getValue().toString());
					} else if (entry.getKey().equals("family")) {
						product.setFamily(entry.getValue().toString());
					}
				}
				productList.add(product);

			}
			return productList;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Listing> ParseListingData(String fileName) {
		ArrayList<Listing> ListingList = new ArrayList<Listing>();
		try {
			JSONParser jsonParser = new JSONParser();

			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) {
				ContainerFactory containerFactory = new ContainerFactory() {
					public List creatArrayContainer() {
						return new LinkedList();
					}

					public Map createObjectContainer() {
						return new LinkedHashMap();
					}

				};
				Map json = (Map) jsonParser.parse(line, containerFactory);
				Iterator iter = json.entrySet().iterator();
				Listing listing = new Listing();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					if (entry.getKey().equals("title")) {
						listing.setTitle(entry.getValue().toString());
					} else if (entry.getKey().equals("manufacturer")) {
						listing.setManufacturer(entry.getValue().toString());
					} else if (entry.getKey().equals("currency")) {
						listing.setCurrency(entry.getValue().toString());
					} else if (entry.getKey().equals("price")) {
						listing.setPrice(entry.getValue().toString());
					}
				}
				ListingList.add(listing);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ListingList;
	}

	public JSONObject createJSONData(Product product) {
		// TODO Auto-generated method stub
		JSONArray listings = new JSONArray();
		ArrayList<Listing> matchedListing = product.getListingList();
		JSONObject listingObj;
		for (int j = 0; j < matchedListing.size(); j++) {
			Map<String, String> listingItems = new LinkedHashMap<String, String>();
			Listing listing = matchedListing.get(j);
			listingItems.put("title", listing.getTitle());
			listingItems.put("manufacturer", listing.getManufacturer());
			listingItems.put("currency", listing.getCurrency());
			listingItems.put("price", listing.getPrice());
			listings.add(listingItems);
		}

		Map<String, Object> items = new LinkedHashMap<String, Object>();
		items.put("product_name", product.getProduct_name());
		items.put("listings", listings);
		JSONObject json = new JSONObject(items);
		return json;
	}
}
