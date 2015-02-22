package edu.csupomona.cs480.data.provider;

import java.util.List;

import edu.csupomona.cs480.data.Food;

public interface FoodManager {
	/**
	 * Get the food information object based on
	 * the given foodPrice.
	 * <p>
	 * If the user does not exist, simply create
	 * one.
	 *
	 * @param foodPrice
	 * @return the Food object
	 */
	public Food getFood(String foodPrice);

	/**
	 * Update the given food object and persist it.
	 * <p>
	 * If the food does not exist before, this
	 * method will create a new record; otherwise,
	 * it will overwrite whatever is currently
	 * being stored.
	 *
	 * @param food object
	 */
	public void updateFood(Food food);

	/**
	 * Delete the given food from the storage.
	 *
     * @param foodId
     */
	public void deleteFood(String foodId);

	/**
	 * List all the current foods in the storage.
	 *
	 * @return
	 */
	public List<Food> listAllFoods();
	
	/**
	 * List all the foods under a given price
	 *
	 * @return
	 */
	public List<Food> listFoodsUnder(String foodPrice);

    /**
     * Get a key for the given food in the foodmap
     *
     * @return
     */
    public String foodToKey (Food f);
}
