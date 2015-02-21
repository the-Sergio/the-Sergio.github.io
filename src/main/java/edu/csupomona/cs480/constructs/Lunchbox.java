package edu.csupomona.cs480.constructs;

import edu.csupomona.cs480.data.Food;
import edu.csupomona.cs480.data.provider.FoodManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jacob on 2/7/2015.
 */
public class Lunchbox {

    @Autowired
    private FoodManager foodManager;

    private List<Food> lunchbox;

    public Lunchbox () {
        lunchbox = new ArrayList<Food>();
    }

    public List<Food> getLunchbox () {
        return lunchbox;
    }

    public void addItem (Food f) {
        lunchbox.add(f);
    }

    public void removeItem (Food f) {
        lunchbox.remove(f);
    }
}
