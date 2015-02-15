package edu.csupomona.cs480;

import edu.csupomona.cs480.constructs.LunchboxManager;
import edu.csupomona.cs480.data.Food;
import edu.csupomona.cs480.data.FoodMap;
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
        LunchboxManager l = new LunchboxManager();
        for (int i = 0; i < 100; i++) {
            String UID1 = l.makeUniqueId();
            String UID2 = l.makeUniqueId();
            assertNotSame(UID1, UID2);
        }
    }
}
