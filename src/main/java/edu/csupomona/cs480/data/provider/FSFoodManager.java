package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.Food;
import edu.csupomona.cs480.data.FoodMap;
import edu.csupomona.cs480.util.ResourceResolver;

/**
 * The implementation of {@link FoodManager} interface
 * using file system.
 * 
 */
public class FSFoodManager implements FoodManager {

	/**
	* persist all the food related objects as JSON.
	*/
	private static final ObjectMapper JSON = new ObjectMapper();
	
	/**
	 * Load the food map from the local file.
	 *
	 * @return
	 */
	
	
	private FoodMap getFoodMap() {
		FoodMap foodMap = null;
		File userFile = ResourceResolver.getUserFile();
		
        if (userFile.exists()) {
        	// read the file and convert the JSON content
        	// to the UserMap object
            try {
				foodMap = JSON.readValue(userFile, FoodMap.class);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
        	foodMap = new FoodMap();
        }
        return foodMap;
	}
	
	/**
	 * Save and persist the user map in the local file.
	 *
	 * @param foodMap
	 */
	private void persistFoodMap(FoodMap foodMap) {
		try {
			// convert the user object to JSON format
            JSON.writeValue(ResourceResolver.getUserFile(), foodMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Food getFood(String foodPrice) {
		FoodMap foodMap = getFoodMap();
        return foodMap.get(foodPrice);
	}

	
	@Override
	public void updateFood(Food food) {
		FoodMap foodMap = getFoodMap();
		foodMap.put(food.getId(), food);
		persistFoodMap(foodMap);
	}

	@Override
	public void deleteFood(String foodId) {
		FoodMap foodMap = getFoodMap();
		foodMap.remove(foodId);
		persistFoodMap(foodMap);
	}

	@Override
	public List<Food> listAllFoods() {
		FoodMap foodMap = getFoodMap();
		return new ArrayList<Food>(foodMap.values());
	}
	
	@Override
	public List<Food> listFoodsUnder(String foodPrice){
		float price = Float.parseFloat(foodPrice);
		
		FoodMap foodMap = getFoodMap();
		
		List<Food> currentList = new ArrayList<Food>(foodMap.values());
		
		List<Food> newList = new ArrayList<Food>();
		
		System.out.println("Parse my price: " + price + " foodprice:" + foodPrice );
		
		for(Food s : currentList ){
			if(Float.parseFloat(s.getPrice()) <= price){
				newList.add(s);
			}
		}
		
		
		return newList;
		
	}


}
