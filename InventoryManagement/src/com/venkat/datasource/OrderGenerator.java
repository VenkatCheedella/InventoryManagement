package com.venkat.datasource;

import java.util.ArrayList;
import java.util.List;

public class OrderGenerator {
	
	public static Order getOrder(int numOfLines, int orderId){
		List<ProductTypes> productTypes = RandomProductTypes.generateRandomProductTypes(numOfLines);
		List<Line> lines = new ArrayList<>();
		for(int i=0; i< numOfLines; ++i){
			Line newLine = new Line(productTypes.get(i), RandomNumberGeneration.getRandomNumber(6));
			lines.add(newLine);
		}
		return new Order(orderId, lines);
	}
}
