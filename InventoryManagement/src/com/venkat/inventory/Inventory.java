package com.venkat.inventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.venkat.datasource.Line;
import com.venkat.datasource.Order;
import com.venkat.exceptions.InvalidProductInfoException;
import com.venkat.response.InventoryServiceResponse;

public class Inventory {
	
	private Map<String, Integer> existingItems;
	
	public Inventory(){
		existingItems = new HashMap<>();		
	}
	
	public void provideServiceToOrder(Order order){		
		Map<String, Integer> requestedOrder = new HashMap<>();
		Map<String, Integer> allotedOrder = new HashMap<>();
		Map<String, Integer> discardedOrder = new HashMap<>();
		List<Line> currentOrderLines = order.getListOfLines();
		for(Line currLine : currentOrderLines){
			String prodType = currLine.getProductType().toString();
			int quantity = currLine.getQunatity();
			int isAllocated = reduceProductSizeFromInventoryForALine(prodType, quantity);
			requestedOrder.put(prodType, isAllocated);
			if(isAllocated != 0){				
				allotedOrder.put(prodType, quantity);
				discardedOrder.put(prodType, 0);
			}else{
				allotedOrder.put(prodType, 0);
				discardedOrder.put(prodType, quantity);
			}
		}
		List<Map<String, Integer>> orderAllocationStatus = new ArrayList<>();
		orderAllocationStatus.add(requestedOrder);
		orderAllocationStatus.add(allotedOrder);
		orderAllocationStatus.add(discardedOrder);
		InventoryServiceResponse.addOrderDeliveryEntry(order.getId(), orderAllocationStatus);
	}
	
	public int reduceProductSizeFromInventoryForALine(String productId, int countOfItems){
		Integer existingProductsCount = existingItems.get(productId);
		if(existingProductsCount == 0){
			existingItems.remove(productId);
		}
		if(existingProductsCount < countOfItems)
			return 0;
		existingItems.replace(productId, existingProductsCount, existingProductsCount-countOfItems);
		return countOfItems;
	}
	
	public int getExistingItemsSize() {
		return existingItems.size();
	}

	public void loadInventory(String inventoryInfo) throws IOException, InvalidProductInfoException{		
		File file = new File(inventoryInfo);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String readLine;
		try{
			while((readLine = reader.readLine()) != null){
				String[] productInfo = readLine.split("\\s");
				if(productInfo != null && productInfo.length==2){
					String productId = productInfo[0];
					Integer countOfProduct = Integer.valueOf(productInfo[1]);				
					if(productId != null && countOfProduct != null)
						existingItems.put(productId, countOfProduct);
				}
				else 
					throw new InvalidProductInfoException("ProductInfo input format is invalid");
			}
		}
		finally{
			reader.close();	
		}		
	}	
}
