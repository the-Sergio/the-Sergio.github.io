//Author: Andy Montes
//This class does custome sorting using guava lib
//Chaining for multiple sorting 
//Andy - test

package edu.csupomona.cs480.util;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import edu.csupomona.cs480.data.Food;


//Andy Montes Assignment 5 part 2 using the Odedring function from googles guave lib
public class SortingManager {

	public List<Food> highToLow(List<Food> list){
        Collections.sort(list, Ordering.natural().onResultOf(new Function<Food, Float>() {

            @Override
            public Float apply (Food arg0) {
                return Float.parseFloat(arg0.getPrice());
            }
        }).reverse());

        return list;
	}

    public List<Food> lowToHigh (List<Food> list) {
        Collections.sort(list, Ordering.natural().onResultOf(new Function<Food, Float>() {

            @Override
            public Float apply (Food arg0) {
                return Float.parseFloat(arg0.getPrice());
            }
        }));

        return list;
    }

    //Example on how we can sort by different topics ...
	//I can sort by "sortBy" then sort by highToLow using Chaining
	public List<Food> sortByChaining(List<Food> list, String sortBy){
		
		if(sortBy.equals("Price")){
			//....
		}else if(sortBy.equals("Location")){
			//...
		}
		return list;
		
	}
}
