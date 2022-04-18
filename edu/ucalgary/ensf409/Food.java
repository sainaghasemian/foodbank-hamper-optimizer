/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 1.3
@since   1.0
*/
package edu.ucalgary.ensf409;

class Food {
    
    //private attributes
    private final int ITEM_ID; 
    private final String FOOD_NAME;
    private final Nutrition NUTRITION;

    //Food constructor     
    public Food(int itemID, String name, int grain, int fv, int pro, int other, int cals) {
        this.ITEM_ID = itemID;
        this.FOOD_NAME = name;
        this.NUTRITION = new Nutrition(cals, grain, fv, pro, other);
    }

    //Food constructor for testing purposes
    public Food(int itemID, String name, Nutrition nutrition) {
        this.ITEM_ID = itemID;
        this.FOOD_NAME = name;
        this.NUTRITION = nutrition;
    }

    
    //Returns the food ItemID 
    public int getItemID() {
        return this.ITEM_ID;
    }
    

    //Returns the food name
    public String getName() {
        return this.FOOD_NAME;
    }

    //Returns the nutrition object 
    public Nutrition getNutrition() {
        return this.NUTRITION;
    }
}