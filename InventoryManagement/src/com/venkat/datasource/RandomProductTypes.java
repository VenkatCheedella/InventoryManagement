package com.venkat.datasource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.venkat.utility.RandomNumberGeneration;

public class RandomProductTypes {

	public static List<ProductTypes> generateRandomProductTypes(int quantity){
		List<ProductTypes> prodTypes = new ArrayList<>();
		HashSet<Integer> generatedIntegers = new HashSet<>();
		//System.out.println("Requested lines : " + quantity);
		while(quantity != 0){
			int generatedRandNum = RandomNumberGeneration.getRandomNumberInRange(0, sizeOfTypesOfProductTypes()-1);
			if(!generatedIntegers.contains(generatedRandNum)){				
				prodTypes.add(ProductTypes.values()[generatedRandNum]);
				generatedIntegers.add(generatedRandNum);
				--quantity;
			}			
		}			
		return prodTypes;
	}
		
	private static int sizeOfTypesOfProductTypes(){
		ProductTypes[] types = ProductTypes.values();
		if(types != null)
			return (types.length > 0) ? types.length : 0;
		else
			return 0;
	}
}
