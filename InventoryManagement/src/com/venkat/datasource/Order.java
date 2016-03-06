package com.venkat.datasource;

import java.util.List;


public class Order {
	
	private int id;
	private List<Line> listOfLines;	

	public Order(int id, List<Line> listOfLines) {		
		this.id = id;
		this.listOfLines = listOfLines;
	}

	public int getId() {
		return id;
	}

	public List<Line> getListOfLines() {
		return listOfLines;
	}
	
		
	
	
	public String convertLinesToString(){
		StringBuilder builder = new StringBuilder(listOfLines.size());
		for(Line line: listOfLines){
			builder.append("{ Product Type : " +  "\"" + line.getProductType() + 
					"\"" +  " , Qunatity : " + "\"" + line.getQunatity() + "\" }");
		}
		return builder.toString();
	}
	
	public String toString(){
		return "Header : "  + id + ", " + "Lines : " + convertLinesToString();
	}
}
