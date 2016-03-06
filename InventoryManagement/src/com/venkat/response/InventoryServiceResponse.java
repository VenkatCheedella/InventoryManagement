package com.venkat.response;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.venkat.datasource.ProductTypes;

public class InventoryServiceResponse {
	private static Map<Integer, List<Map<String, Integer>>> orderDeliveryInformation;
	static{
		orderDeliveryInformation = new LinkedHashMap<>();
	}

	public static void addOrderDeliveryEntry(Integer orderId, List<Map<String, Integer>> orderDeliveryEntry){
		orderDeliveryInformation.put(orderId, orderDeliveryEntry);
	}
	
	private String[] getProductTypes(){
		ProductTypes[] prodTypes = ProductTypes.values();
		String[] products = new String[prodTypes.length];
		for(int i=0; i< prodTypes.length; ++i){
			products[i] = prodTypes[i].toString();
		}
		return products;
	}
	
	public String retrieveOrderInfo(List<Map<String, Integer>> orderInfo){
		String[] listOfProducts = getProductTypes();
		StringBuilder orderInfoInString = new StringBuilder();
		for(Map<String, Integer> orderEntry : orderInfo){
			for(String product : listOfProducts){
				if(orderEntry.containsKey(product)){
					orderInfoInString.append(orderEntry.get(product).toString() + ",");
				}else{
					orderInfoInString.append("0,");
				}
			}
			orderInfoInString.append("::");
		}
		return orderInfoInString.toString();
	}
	
	public String toString(){
		Set<Integer> orderIds = orderDeliveryInformation.keySet();
		StringBuilder builder = new StringBuilder();	
		builder.append("\nOrder# :" + " ReqOrder " + "    Alloted " + "   ExcessOrder \n");
		for(Integer orderId: orderIds){
			builder.append("   " + orderId.toString() + "   : " + retrieveOrderInfo(orderDeliveryInformation.get(orderId)));			
			builder.append("\n");
		}
		return builder.toString();
	}
	
}
