package edu.csupomona.cs480.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.Food;
import edu.csupomona.cs480.data.provider.FoodManager;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
    @Autowired
    private FoodManager foodManager;

    /**
     * This is a simple example of how the HTTP API works.
     * It returns a String "OK" in the HTTP response.
     * To try it, run the web application locally,
     * in your web browser, type the link:
     * 	http://localhost:8080/cs480/ping
     */
    @RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
    String healthCheck() {
    	// You can replace this with other string,
    	// and run the application locally to check your changes
    	// with the URL: http://localhost:8080/
        return "OK RUNNING";
    }

    /**
     * This is a simple example of how to use a data manager
     * to retrieve the data and return it as an HTTP response.
     * <p>
     * Note, when it returns from the Spring, it will be
     * automatically converted to JSON format.
     * <p>
     * Try it in your web browser:
     * 	http://localhost:8080/cs480/user/user101
     */
    @RequestMapping(value = "/cs480/user/{foodId}", method = RequestMethod.GET)
    Food getFood(@PathVariable("foodId") String foodId) {
    	Food food = foodManager.getFood(foodId);
        return food;
    }

    /**
     * This is an example of sending an HTTP POST request to
     * update a food's information (or create the food if not
     * exists before).
     *
     * You can test this with a HTTP client by sending
     *  http://localhost:8080/cs480/user/user101
     *  	name=John major=CS
     *
     * Note, the URL will not work directly in browser, because
     * it is not a GET request. You need to use a tool such as
     * curl.
     *
     * @param id
     * @param price
     * @param description
     * @return
     */
    @RequestMapping(value = "/cs480/user/{foodId}", method = RequestMethod.POST)
    Food updateFood(
    		@PathVariable("foodId") String id,
    		@RequestParam("price") String price,
    		@RequestParam(value = "description", required = false) String description) {
    	Food food = new Food();
    	food.setId(id);
    	food.setPrice(price);
    	food.setDescription(description);
    	foodManager.updateFood(food);
    	return food;
    }

    /**
     * This API deletes the food. It uses HTTP DELETE method.
     *
     * @param foodId
     */
    @RequestMapping(value = "/cs480/user/{foodId}", method = RequestMethod.DELETE)
    void deleteFood(
    		@PathVariable("foodId") String foodId) {
    	foodManager.deleteFood(foodId);
    }

    /**
     * This API list the food for the given price.
     *
     * @param price
     */
    @RequestMapping(value = "/cs480/food/{price}", method = RequestMethod.GET)
    List<Food> getPriceList(
    		@PathVariable("price") String price) {
   	price = price.replace('_', '.');
    	List<Food> LF = foodManager.listFoodsUnder(price);
    	return LF;
    }

    /**
     * This API returns if there are foods in the database.
     *
     * For assignment 3, by Jacob Buchowiecki.
     */
    @RequestMapping(value = "/cs480/food/hasFoods", method = RequestMethod.GET)
    boolean hasFoods(){
        return !foodManager.listAllFoods().isEmpty();
    }
    
    /**
     * This API lists all the foods in the current database.
     *
     * @return
     */
    @RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
    List<Food> listAllFoods() {
    	return foodManager.listAllFoods();
    }

    /*********** Web UI Test Utility **********/
    /**
     * This method provide a simple web UI for you to test the different
     * functionalities used in this web service.
     */
    @RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
    ModelAndView getUserHomepage() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("foods", listAllFoods());
        
        return modelAndView;
    }
    /**
     * Test API for A3
     *
     * Claude Phan
     */
    @RequestMapping(value = "/cs480/ClaudeAPI", method = RequestMethod.GET)
    String Claude() {
    	
        return "OK RUNNING";
    }
    
    /**
     *Roberto Rodriguez API
     */
    @RequestMapping(value = "/cs480/Roberto", method = RequestMethod.GET)
    String Roberto() {
    	
        return "Roberto!";
    }
    
}