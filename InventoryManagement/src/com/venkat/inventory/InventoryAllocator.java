package com.venkat.inventory;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

import com.venkat.datasource.Order;
import com.venkat.exceptions.InvalidProductInfoException;

public class InventoryAllocator implements Callable<Boolean>{

	private BlockingQueue<Order> ordersQueue;
	private Inventory inventory;	

	InventoryAllocator(BlockingQueue<Order> ordersQueue, String inventoryStockInfo) throws IOException, InvalidProductInfoException{
		this.ordersQueue = ordersQueue;
		inventory = new Inventory();
		inventory.loadInventory(inventoryStockInfo);
	}
	
	@Override
	public Boolean call() throws Exception {
		Order currentOrder = null;
		while(true){	
			if(inventory.isInventoryEmpty())
				return true;
			currentOrder  = ordersQueue.take();	
			inventory.provideServiceToOrder(currentOrder);																
		}		
	}
}
