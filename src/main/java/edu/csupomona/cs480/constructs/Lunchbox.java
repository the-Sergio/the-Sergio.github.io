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

    public double getLunchboxTotal () {
        Food[] temp = new Food[lunchbox.size()];
        lunchbox.toArray(temp);
        double total = 0.0;
        for (int i = 0; i < temp.length; i++) {
            total += Double.parseDouble(temp[i].getPrice());
        }
        return total;
    }

    //Makes a lunchbox using the most expensive item that can fit in the lunchbox each time
    public List<Food> makeLunchboxMax (String maxPrice) {
        float mPrice = Float.parseFloat(maxPrice);
        List<Food> fList = foodManager.listFoodsUnder(maxPrice);
        List<Food> lunchbox = new ArrayList<Food>();
        while (!fList.isEmpty()) {
            lunchbox.add(fList.get(0));
            mPrice -= Float.parseFloat(fList.get(0).getPrice());
            fList = foodManager.listFoodsUnder(String.valueOf(mPrice));
        }
        return fList;
    }

    //Makes a lunchbox using the least expensive item that can fit in the lunchbox each time
    public List<Food> makeLunchboxMin (String maxPrice) {
        float mPrice = Float.parseFloat(maxPrice);
        List<Food> fList = foodManager.listFoodsUnder(maxPrice);
        List<Food> lunchbox = new ArrayList<Food>();
        while (!fList.isEmpty()) {
            lunchbox.add(fList.get(fList.size() - 1));
            mPrice -= Float.parseFloat(fList.get(fList.size() - 1).getPrice());
            fList = foodManager.listFoodsUnder(String.valueOf(mPrice));
        }
        return fList;
    }

    //Makes a lunchbox using random items that can fit in the lunchbox each time
    public List<Food> makeLunchboxRand (String maxPrice) {
        float mPrice = Float.parseFloat(maxPrice);
        List<Food> fList = foodManager.listFoodsUnder(maxPrice);
        List<Food> lunchbox = new ArrayList<Food>();
        Random r = new Random();
        while (!fList.isEmpty()) {
            int itemIndex = r.nextInt(fList.size() - 1);
            lunchbox.add(fList.get(itemIndex));
            mPrice -= Float.parseFloat(fList.get(itemIndex).getPrice());
            fList = foodManager.listFoodsUnder(String.valueOf(mPrice));
        }
        return fList;
    }
}
