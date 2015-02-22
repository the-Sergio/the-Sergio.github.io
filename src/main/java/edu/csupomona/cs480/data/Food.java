package edu.csupomona.cs480.data;

public class Food {
	
	/** The unique post title */
	private String id;
	/** The unique description for a post*/
	private String description;
	/** The price for the post description for a post*/
	private String price;
	
	
		
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
