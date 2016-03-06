package com.venkat.inventory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.venkat.datasource.Order;
import com.venkat.datasource.OrderGenerator;
import com.venkat.datasource.RandomNumberGeneration;
import com.venkat.exceptions.InvalidProductInfoException;
import com.venkat.response.InventoryServiceResponse;

class OrderProducer implements Runnable{

	private Queue<Order> orders;	

	public OrderProducer(Queue<Order> orders) {		
		this.orders = orders;
	}

	@Override
	public void run() {
		Order order;
		int orderId=0;
		while(true){
			order = OrderGenerator.getOrder(RandomNumberGeneration.getRandomNumber(6), orderId);
			try {
				//synchronized(orders)
				//{
					orders.add(order);
					//orders.notify();
				//}
				System.out.println("Request added");
				++orderId;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}

}


public class TestInventoryAllocator {

	public static void main(String[] args) {
		try {
			Queue<Order> inputQueue = new LinkedList<Order>();				
			OrderProducer producer = new OrderProducer(inputQueue);
			Thread producerThread = new Thread(producer);
			producerThread.start();
			InventoryAllocator orderConsumer = new InventoryAllocator(inputQueue, "src/inventoryinfo.txt");
			Thread consumerThread = new Thread(orderConsumer);
			consumerThread.start();
			InventoryServiceResponse inventoryServiceResponse = new InventoryServiceResponse();
			producerThread.join();
			consumerThread.join();
			System.out.println(inventoryServiceResponse.toString());			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidProductInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
