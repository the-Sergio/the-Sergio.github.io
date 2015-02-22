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
        Food[] temp = new Food[lunchbox.size()];
        List<Food> newBox = new ArrayList<Food>();
        lunchbox.toArray(temp);
        boolean found = false;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].getId().equals(f.getId())
                    && temp[i].getPrice().equals(f.getPrice())
                    && temp[i].getDescription().equals(f.getDescription())
                    && !found) {
                //skip this item, it is the item we want to remove, only the first time though
                found = true;
            } else {
                newBox.add(temp[i]);
            }
        }
        lunchbox = newBox;
    }
}
