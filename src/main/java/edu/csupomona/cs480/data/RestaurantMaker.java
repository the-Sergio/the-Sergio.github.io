//For Claude Phan A9

package edu.csupomona.cs480.data;

public class RestaurantMaker {
	
 	public void RestaurantGreenting(Restaurant r) {
 		r.greet();
 	}
}
	 
	 abstract class Restaurant {
	 	public abstract String greet();
	 }
	 
	 
	 
	 class TacoBell extends Restaurant  {
	 	public String name;
	 	
		 public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String greet() {
			String temp = "Welcome to " + name + ". Why not try the nachos?";
			return temp;

	 	}
	 }
	 
	 class Carls extends Restaurant  {
		
			 public String name;
			 	
			 public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String greet() {
				String temp = "Welcome to " + name + ". Why not try the new smoothies?";
				return temp;

		 	}	
		 }
	   

