package com.venkat.datasource;

public class Line {
	private ProductTypes productType;
	private int Qunatity ;
			
	public Line(ProductTypes productType, int qunatity) {		
		this.productType = productType;
		Qunatity = qunatity;
	}
	public ProductTypes getProductType() {
		return productType;
	}
	public int getQunatity() {
		return Qunatity;
	}	
}
