package com.venkat.datasource;

import java.util.Random;

public class RandomNumberGeneration {
	
	public static int getRandomNumber(int range){
		Random randNum = new Random();
		return randNum.nextInt(range);
	}
	
	public static int getRandomNumberInRange(int lowerLimit, int higherLimit){
		int difference = higherLimit -lowerLimit +1;
		int randomNum = new Random().nextInt(Integer.MAX_VALUE-1)%difference;
		return lowerLimit + randomNum;
	}
}
