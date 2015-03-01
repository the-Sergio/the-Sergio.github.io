package edu.csupomona.cs480;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.csupomona.cs480.constructs.LunchboxManager;
import edu.csupomona.cs480.data.Food;
import edu.csupomona.cs480.data.FoodMap;
import edu.csupomona.cs480.data.provider.FoodManager;
import edu.csupomona.cs480.util.SortingManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    //Jacob Test Case 1
    //Assignment 6
    public void testFoodMap () {
        FoodMap f = new FoodMap();
        Food f1 = new Food();
        f1.setId("Food 1");
        f1.setPrice("2.99");
        Food f2 = new Food();
        f2.setId("Food 2");
        f2.setPrice("2.99");
        f.put("Food 1", f1);
        f.put("Food 2", f2);
        assertEquals(f.get("Food 1").getId(), "Food 1");
        assertEquals(f.get("Food 2").getId(), "Food 2");
    }

    //Jacob Test Case 2
    //Assignment 6
    public void testUID () {
        LunchboxManager l = LunchboxManager.getInstance();
        for (int i = 0; i < 100; i++) {
            String UID1 = l.makeUniqueId();
            String UID2 = l.makeUniqueId();
            assertNotSame(UID1, UID2);
        }
    }
    
    //Andy Montes
    //Assignemnt 6
    @Autowired
    private FoodManager foodManager;
    public void testSorting(){
    	System.out.println("------List Size Test Not Same------");
    	SortingManager sm = new SortingManager();
    	List<Food> l1 = new ArrayList<Food>();
    	List<Food> l2 = new ArrayList<Food>();
    	
    	Food f1 = new Food();
        f1.setId("Food 1");
        f1.setPrice("2.99");
        Food f2 = new Food();
        f2.setId("Food 2");
        f2.setPrice("4.99");
        
        //different list 
        l1.add(f1);
        l1.add(f2);
        
        //different list
        l2.add(f1);
        l1.add(f2);
        
        //after sorting second list, they should not be the same
        sm.highToLow(l2);
       
        assertNotSame(l1,l2);
        
    }
    
    //Claude Phan
    //A6
    public void TestGetItem () {
       FoodMap f = new FoodMap();
       Food testFood1 = new Food();
       testFood1.setId("Orange Chicken");
       testFood1.setDescription("Panda Express");
       testFood1.setPrice("3.69");
       f.put("TestFood1", testFood1);
       assertEquals(f.get("TestFood1").getId(), "Orange Chicken");
       assertEquals(f.get("TestFood1").getPrice(), "3.69");
       assertEquals(f.get("TestFood1").getDescription(), "PandaExpress");
       
   }
    
}
