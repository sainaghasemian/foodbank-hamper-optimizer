package edu.ucalgary.ensf409;

class Food {
    
    //private attributes
    private final String ITEM_ID;
    private final String FOOD_NAME;
    private final Nutrition NUTRITION;

    //Food constructor     
    public Food(String id, String name, int grain, int fv, int pro, int other, int cals) {
        this.ITEM_ID = id;
        this.FOOD_NAME = name;
        this.NUTRITION = new Nutrition(cals, grain, fv, pro, other);
    }

    //Returns the food ItemID 
    public String getItemID() {
        return this.ITEM_ID;
    }

    //Returns the food name
    public String getName() {
        return this.FOOD_NAME;
    }

    public Nutrition getNutrition() {
        return this.NUTRITION;
    }
}