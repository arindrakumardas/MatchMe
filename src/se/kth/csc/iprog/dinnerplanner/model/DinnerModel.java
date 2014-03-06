package se.kth.csc.iprog.dinnerplanner.model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;


public class DinnerModel extends Observable implements IDinnerModel{
	
	public static int STARTER = 1, MAIN = 2, DESSERT = 3, GUESTS = 4; //For notifications

	Set<Dish> dishes = new HashSet<Dish>();
	Set<Dish> selectedDishes = new HashSet<Dish>();
	
	/**
	 * TODO: For Lab2 you need to implement the IDinnerModel interface.
	 * When you do this you will have all the needed fields and methods
	 * for the dinner planner (number of guests, selected dishes, etc.). 
	 */
	
	public int numberOfGuests;
	
	
	/**
	 * The constructor of the overall model. Set the default values here
	 */
	
	public DinnerModel(){
		
		//Adding some example data, you can add more
		Dish dish1 = new Dish("French toast",Dish.STARTER,"toast","In a large mixing bowl, beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. Soak bread slices in the egg mixture until saturated. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides, sprinkle with cinnamon and serve hot.");
		Ingredient dish1ing1 = new Ingredient("Eggs",0.5,"",1);
		Ingredient dish1ing2 = new Ingredient("Milk",30,"ml",6);
		Ingredient dish1ing3 = new Ingredient("Brown sugar",7,"g",1);
		Ingredient dish1ing4 = new Ingredient("Ground nutmeg",0.5,"g",12);
		Ingredient dish1ing5 = new Ingredient("White bread",2,"slices",2);
		dish1.addIngredient(dish1ing1);
		dish1.addIngredient(dish1ing2);
		dish1.addIngredient(dish1ing3);
		dish1.addIngredient(dish1ing4);
		dish1.addIngredient(dish1ing5);
		dishes.add(dish1);
		
		Dish dish2 = new Dish("Meat balls",Dish.MAIN,"meatballs","Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
		Ingredient dish2ing1 = new Ingredient("Extra lean ground beef",115,"g",20);
		Ingredient dish2ing2 = new Ingredient("Sea salt",0.7,"g",3);
		Ingredient dish2ing3 = new Ingredient("Small onion, diced",0.25,"",2);
		Ingredient dish2ing4 = new Ingredient("Garlic salt",0.6,"g",3);
		Ingredient dish2ing5 = new Ingredient("Italian seasoning",0.3,"g",3);
		Ingredient dish2ing6 = new Ingredient("Dried oregano",0.3,"g",3);
		Ingredient dish2ing7 = new Ingredient("Crushed red pepper flakes",0.6,"g",3);
		Ingredient dish2ing8 = new Ingredient("Worcestershire sauce",16,"ml",7);
		Ingredient dish2ing9 = new Ingredient("Milk",20,"ml",4);
		Ingredient dish2ing10 = new Ingredient("Grated Parmesan cheese",5,"g",8);
		Ingredient dish2ing11 = new Ingredient("Seasoned bread crumbs",115,"g",4);
		dish2.addIngredient(dish2ing1);
		dish2.addIngredient(dish2ing2);
		dish2.addIngredient(dish2ing3);
		dish2.addIngredient(dish2ing4);
		dish2.addIngredient(dish2ing5);
		dish2.addIngredient(dish2ing6);
		dish2.addIngredient(dish2ing7);
		dish2.addIngredient(dish2ing8);
		dish2.addIngredient(dish2ing9);
		dish2.addIngredient(dish2ing10);
		dish2.addIngredient(dish2ing11);
		dishes.add(dish2);
		
		Dish dish3 = new Dish("Baked Brie",Dish.STARTER,"bakedbrie","Preheat the oven to 350 degrees F. Place the brie on a sheet pan covered with parchment paper and drizzle with the honey. Bake for 5 to 7 minutes, or until it starts to ooze but not melt. Serve with crackers.");
		Ingredient dish3ing1 = new Ingredient("Wheel brie", 0.25, "",20);
		Ingredient dish3ing2 = new Ingredient("Honey",4,"tablespoons",40);
		dish3.addIngredient(dish3ing1);
		dish3.addIngredient(dish3ing2);
		dishes.add(dish3);
		
		Dish dish4 = new Dish("Chicken Parmesan",Dish.MAIN,"chickparmesan","Preheat the oven to 500 degrees F. Stir the oil and herbs in a small bowl to blend. Season with salt and pepper. Brush both sides of the cutlets with the herb oil. Heat a heavy large oven-proof skillet over high heat. Add the cutlets and cook just until brown, about 2 minutes per side. Remove the skillet from the heat. Spoon the marinara sauce over and around the cutlets. Sprinkle 1 teaspoon of mozzarella over each cutlet, then sprinkle 2 teaspoons of Parmesan over each. Sprinkle the butter pieces atop the cutlets. Bake until the cheese melts and the chicken is cooked through, about 3 to 5 minutes.");
		Ingredient dish4ing1 = new Ingredient("Olive oil",3,"tablespoon",20);
		Ingredient dish4ing2 = new Ingredient("Fresh thyme leaves, chopped",1,"teaspoon",3);
		Ingredient dish4ing3 = new Ingredient("Fresh rosemary leaves, chopped",2,"teaspoon",2);
		Ingredient dish4ing4 = new Ingredient("Fresh Italian parsley leaves, chopped",1,"teaspoon",3);
		Ingredient dish4ing5 = new Ingredient("Salt and freshly grounded black pepper",1,"g",3);
		Ingredient dish4ing6 = new Ingredient("Chicken cutlets",8,"pcs",80);
		Ingredient dish4ing7 = new Ingredient("Tomato sauce",1.5,"cups",30);
		Ingredient dish4ing8 = new Ingredient("Mozzarella, shredded",0.5,"cup",8);
		Ingredient dish4ing9 = new Ingredient("Parmesan, grated",17,"teaspoon",35);
		Ingredient dish4ing10 = new Ingredient("Unsalted butter, cut into pieces",2,"tablespoon",8);
		dish4.addIngredient(dish4ing1);
		dish4.addIngredient(dish4ing2);
		dish4.addIngredient(dish4ing3);
		dish4.addIngredient(dish4ing4);
		dish4.addIngredient(dish4ing5);
		dish4.addIngredient(dish4ing6);
		dish4.addIngredient(dish4ing7);
		dish4.addIngredient(dish4ing8);
		dish4.addIngredient(dish4ing9);
		dish4.addIngredient(dish4ing10);
		dishes.add(dish4);
		
		Dish dish5 = new Dish("Cinnamon Ice-cream",Dish.DESERT,"cinnamonicecream","In a saucepan over medium-low heat, stir together the sugar and half-and-half. When the mixture begins to simmer, remove from heat, and whisk half of the mixture into the eggs. Whisk quickly so that the eggs do not scramble. Pour the egg mixture back into the saucepan, and stir in the heavy cream. Continue cooking over medium-low heat, stirring constantly, until the mixture is thick enough to coat the back of a metal spoon. Remove from heat, and whisk in vanilla and cinnamon. Set aside to cool. Pour cooled mixture into an ice cream maker, and freeze according to the manufacturer's instructions.");
		Ingredient dish5ing1 = new Ingredient("White sugar",1,"cup",9);
		Ingredient dish5ing2 = new Ingredient("Half-and-half cream",1.5,"cups",15);
		Ingredient dish5ing3 = new Ingredient("Eggs, beaten",2,"",6);
		Ingredient dish5ing4 = new Ingredient("Heavy cream",1,"cup",15);
		Ingredient dish5ing5 = new Ingredient("Vanilla extract",1,"teaspoon",3);
		Ingredient dish5ing6 = new Ingredient("Ground cinnamon",2,"teaspoon",5);
		dish5.addIngredient(dish5ing1);
		dish5.addIngredient(dish5ing2);
		dish5.addIngredient(dish5ing3);
		dish5.addIngredient(dish5ing4);
		dish5.addIngredient(dish5ing5);
		dish5.addIngredient(dish5ing6);
		dishes.add(dish5);
		
		Dish dish6 = new Dish("Green Vegan Collard Wraps",Dish.MAIN,"veganwraps","To prepare collard leaves wash leaves, cut off white stem at the bottom that has no leaves and place them in a bath of warm water with juice of half a lemon. Let soak for 10 minutes. Dry the leaves off with paper towels and using a knife thinly slice down the central root (to make it easier to bend the leaves for wrapping). Slice avocado and pepper. In a food processor combine pecans, tamari, cumin (or garlic ginger mix) and olive oil. Pulse until combined and mixture clumps together. Place a collard leaf in front of you and layer nut mix, red pepper slices, avocado slices, a drizzle of lime juice and alfalfa sprouts. Fold over the top and bottom and then wrap up the sides. Slice in half and serve.");
		Ingredient dish6ing1 = new Ingredient("Collard leaves, large",4,"",4);
		Ingredient dish6ing2 = new Ingredient("Red bell pepper",1,"",6);
		Ingredient dish6ing3 = new Ingredient("Avocado",1,"",10);
		Ingredient dish6ing4 = new Ingredient("Alfalfa sprouts",3,"ounces",14);
		Ingredient dish6ing5 = new Ingredient("Lime",0.5,"",6);
		Ingredient dish6ing6 = new Ingredient("Raw pecans",1,"cup",20);
		Ingredient dish6ing7 = new Ingredient("Tamari (gluten-free soy sauce)",1,"tablespoon",20);
		Ingredient dish6ing8 = new Ingredient("Cumin",1,"teaspoon",4);
		Ingredient dish6ing9 = new Ingredient("Extra virgin olive oil",1,"teaspoon",10);
		dish6.addIngredient(dish6ing1);
		dish6.addIngredient(dish6ing2);
		dish6.addIngredient(dish6ing3);
		dish6.addIngredient(dish6ing4);
		dish6.addIngredient(dish6ing5);
		dish6.addIngredient(dish6ing6);
		dish6.addIngredient(dish6ing7);
		dish6.addIngredient(dish6ing8);
		dish6.addIngredient(dish6ing9);
		dishes.add(dish6);
		
		//Initiate the default number of guests
		setNumberOfGuests(4);
		selectedDishes.add(dish1);
		selectedDishes.add(dish2);
		selectedDishes.add(dish5);
	}

	/**
	 * Set the selected dish of the given type.
	 * @param dish
	 * @param type can be {@link Dish#STARTER}, {@link Dish#MAIN} or {@link Dish#DESERT}
	 */
	public void selectDish(Dish dish) {
		selectedDishes.remove(getSelectedDish(dish.getType()));
		selectedDishes.add(dish);
		setChanged();
		notifyObservers(dish.getType()); //Notify which dish was changed.
	}
	
	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishes(){
		return dishes;
	}
	
	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishesOfType(int type){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type){
				result.add(d);
			}
		}
		return result;
	}
	
	/**
	 * Returns the set of dishes of specific type, that contain filter in their name
	 * or name of any ingredient. 
	 */
	public Set<Dish> filterDishesOfType(int type, String filter){
		Set<Dish> result = new HashSet<Dish>();
		for(Dish d : dishes){
			if(d.getType() == type && d.contains(filter)){
				result.add(d);
			}
		}
		return result;
	}

	@Override
	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	@Override
	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
		setChanged();
		notifyObservers(GUESTS);
	}

	@Override
	public Dish getSelectedDish(int type) {
		for(Dish dish : selectedDishes) {
			if(dish.getType() == type) {
				return dish;
			}
		}
		return null;
	}

	@Override
	public Set<Dish> getFullMenu() {
		return selectedDishes;
	}

	@Override
	public Set<Ingredient> getAllIngredients() {
		Set<Ingredient> ingredients = new HashSet<Ingredient>();
		for(Dish dish : selectedDishes) {
			ingredients.addAll(dish.getIngredients());
		}
		return ingredients;
	}

	@Override
	public float getTotalMenuPrice() {
		float price = 0;
		for(Ingredient ingredient : getAllIngredients()) {
			price += ingredient.getPrice()*numberOfGuests;
		}
		return price;
	}
	
	

}
