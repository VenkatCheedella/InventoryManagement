package com.venkat.datasource;

public class ProductRequest {
	
	private ProductTypes producttype;
	private int quantity;
				
	private ProductRequest(ProductTypes producttype, int quantity) {		
		this.producttype = producttype;
		this.quantity = quantity;
	}

	public ProductTypes getProducttype() {
		return producttype;
	}

	public int getQuantity() {
		return quantity;
	}

	public static ProductRequest getANewProductRequest(ProductTypes type){				
		int quantity = RandomNumberGeneration.getRandomNumber(5);
		return new ProductRequest(type, quantity);
	}
	
	
}
