package com.venkat.inventory;

import java.io.IOException;
import java.util.Queue;
import com.venkat.datasource.Order;
import com.venkat.exceptions.InvalidProductInfoException;

public class InventoryAllocator implements Runnable{

	private Queue<Order> ordersQueue;
	private Inventory inventory;

	public InventoryAllocator(Queue<Order> ordersQueue, String inventoryStockInfo) throws IOException, InvalidProductInfoException{
		this.ordersQueue = ordersQueue;
		inventory = new Inventory();
		inventory.loadInventory(inventoryStockInfo);
	}

	public void serviceOrderRequest() throws InterruptedException{
		int curProdSize = inventory.getExistingItemsSize();
		int requestsCount = 5;

	}

	@Override
	public void run() {
		try {
			//serviceOrderRequest();	
			Order currentOrder = null;
			while(true){
				//synchronized (ordersQueue) {
//					if(ordersQueue.isEmpty())	{
//					 ordersQueue.wait();
//					}
				if(!ordersQueue.isEmpty()){
					currentOrder  = ordersQueue.remove();											
					System.out.println("Request removed");
				}					
				//}					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
