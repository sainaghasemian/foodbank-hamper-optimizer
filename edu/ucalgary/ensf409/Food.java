package edu.ucalgary.ensf409;

class Food {
    
    //private attributes
    private final String ITEM_ID;
    private final String FOOD_NAME;
    private final Nutrition NUTRITION;

    public Food(String id, String name, int grain, int fv, int pro, int other, int cals) {

    }

    //Returns the food ItemID 
    public String getItemID() {
        return this.ITEM_ID;
    }

    //Returns the food name
    public String getName() {
        return this.FOOD_NAME;
    }

    public String getNutrition() {
        return this.NUTRITION;
    }
}