package edu.csupomona.cs480.controller;

import java.util.List;
import java.io.IOException;

import edu.csupomona.cs480.constructs.Lunchbox;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.csupomona.cs480.constructs.LunchboxManager;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.Email;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.Food;
import edu.csupomona.cs480.data.provider.FoodManager;
import edu.princeton.cs.introcs.StdAudio;

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

    @Autowired
    private LunchboxManager lunchboxManager;

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
     *	andy montes
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
    	
        return "Roberto was here";
    }
    
    /**
     * Assignment 5
     * Roberto Rodriguez
     * 
     * Plays simple sounds
     */
    @RequestMapping(value = "/cs480/sound", method = RequestMethod.GET)
    String soundTest(){
        
        // scale increments
        int[] steps = { 0, 2, 4, 5, 7, 9, 11, 12 };
        
        for (int i = 0; i < steps.length; i++) {
            double hz = 440.0 * Math.pow(2, steps[i] / 12.0);
            int N = (int) (StdAudio.SAMPLE_RATE * .5);
            double[] a = new double[N+1];
            for (int j = 0; j <= N; j++)
                a[j] = .5 * Math.sin(2 * Math.PI * j * hz / StdAudio.SAMPLE_RATE);
            StdAudio.play(a);
        }

        //StdAudio.close();
        return "BRING THE NOISE!";
    }

    /**
	   * Test API for A4
      *
		* Claude Phan
		*/
		@RequestMapping(value = "/cs480/ClaudeJsoup", method = RequestMethod.GET)
		String Claude1() {
		   Document doc;
		  	try {
		     
		  		// need http protocol
		 		doc = Jsoup.connect("http://google.com").get();
		    
		  		// get page title
		  		String title = doc.title();
		  		System.out.println("title : " + title);
		     
		 		// get all links
		 		Elements links = doc.select("a[href]");
		  		for (Element link : links) {
		     
				// get the value from href attribute
				System.out.println("\nlink : " + link.attr("href"));
				System.out.println("text : " + link.text());
		     
		  		}
		     
		  	} catch (IOException e) {
		    e.printStackTrace();
	  	}
		    	
		return "OK RUNNING";
		}
    
    /* Assignment 5 Maven API
     * Jacob Buchowiecki
     */
    @RequestMapping(value = "/cs480/Email/{email}/{message}", method = RequestMethod.GET)
    boolean emailTest (@PathVariable("email") String email, @PathVariable("message") String message) {
        Email mail = new SimpleEmail();
        mail.setHostName("smtp.gmail.com");
        mail.setSmtpPort(465);
        mail.setAuthenticator(new DefaultAuthenticator("crappytest93992@gmail.com", "111111112"));
        mail.setSSLOnConnect(true);
        try {
            mail.setFrom("crappytest93992@gmail.com");
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
        mail.setSubject("Test Mail");
        try {
            mail.setMsg(message);
            mail.addTo(email);
            mail.send();
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/cs480/lunchbox/{UID}", method = RequestMethod.GET)
    List<Food> getLunchboxFoods (@PathVariable("UID") String UID) {
        return lunchboxManager.getLunchbox(UID).getLunchbox();
    }

    @RequestMapping(value = "/cs480/lunchbox/{UID}", method = RequestMethod.DELETE)
    void removeLunchbox (@PathVariable("UID") String UID) {
        lunchboxManager.remLunchbox(UID);
    }

    @RequestMapping(value = "/cs480/lunchbox/add", method = RequestMethod.POST)
    String addFoodToLunchbox (@RequestParam("UID") String UID
            , @RequestParam("Description") String description
            , @RequestParam("ID") String id
            , @RequestParam("Price") String price) {
        Food f = new Food();
        f.setId(id);
        f.setPrice(price);
        f.setDescription(description);
        Food temp = foodManager.getFood(foodManager.foodToKey(f));
        lunchboxManager.getLunchbox(UID).addItem(temp);
        return temp.getPrice();
    }

    @RequestMapping(value = "/cs480/lunchbox/rem", method = RequestMethod.POST)
    String remFoodFromLunchbox (@RequestParam("UID") String UID
            , @RequestParam("Description") String description
            , @RequestParam("ID") String id
            , @RequestParam("Price") String price) {
        Food f = new Food();
        f.setId(id);
        f.setPrice(price);
        f.setDescription(description);
        Food temp = foodManager.getFood(foodManager.foodToKey(f));
        lunchboxManager.getLunchbox(UID).removeItem(temp);
        return temp.getPrice();
    }

    @RequestMapping(value = "/cs480/lunchbox/getUID", method = RequestMethod.GET)
    String getUID () {
        String UID = lunchboxManager.makeUniqueId();
        lunchboxManager.addLunchbox(UID, new Lunchbox());
        return UID;
    }
    
    /**
     * Jenkins test
     */
    @RequestMapping(value = "/cs480/Jenkins", method = RequestMethod.GET)
    String JenkinsTest() {
    	
        return "Jenkins working";
    }
}