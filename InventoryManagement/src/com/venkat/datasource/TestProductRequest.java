package com.venkat.datasource;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestProductRequest implements Runnable {
	
	private int id;
	
	public TestProductRequest(int id) {
		this.id =id;
	}
		
	@Override
	public void run() {
		dataSource();
	}
	
	public void dataSource(){
		int orderId =1;
		for(int i=0; i < 5; ++i){
			Order order = OrderGenerator.getOrder(RandomNumberGeneration.getRandomNumber(6), orderId);
			System.out.print(id + " ");
			System.out.println(order.toString());
			System.out.println();
			++orderId;
		}
	}
	
	public static void main(String[] args) {		
		final ExecutorService executor = Executors.newFixedThreadPool(2);
		int i=1;
		System.out.println("Initiating Data Source creation");
		while(i <=2){
			executor.execute(new TestProductRequest(i));
			++i;
		}		
		executor.shutdown();
		while(!executor.isTerminated()){				
		}
		System.out.println("DataSources are created");
	}
}
