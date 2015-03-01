package edu.csupomona.cs480.constructs;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Jacob on 2/8/2015.
 */
public class LunchboxManager {

    private static LunchboxManager INSTANCE;

    private HashMap<String, Lunchbox> lunchboxes;
    private Random r;

    private LunchboxManager () {
        lunchboxes = new HashMap<String, Lunchbox>();
        r = new Random();
    }

    public static LunchboxManager getInstance () {
        if (INSTANCE == null) {
            INSTANCE = new LunchboxManager();
        }
        return INSTANCE;
    }

    public void addLunchbox (String id, Lunchbox l) {
        lunchboxes.put(id, l);
    }

    public Lunchbox getLunchbox (String id) {
        return lunchboxes.get(id);
    }

    public void remLunchbox (String id) {
        lunchboxes.remove(id);
        System.out.println("User removed, id: " + id);
    }

    public String makeUniqueId () {
        String id = "";
        do {
            id = new BigInteger(130, r).toString(32);
        } while (lunchboxes.keySet().contains(id));
        System.out.println("User made, id: " + id);
        return id;
    }
}
