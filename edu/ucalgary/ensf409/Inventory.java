/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 2.4
@since   1.0
*/
package edu.ucalgary.ensf409;

import java.util.ArrayList;
 
public class Inventory {
 
    //This method finds all the possible food combinations for each hamper,
    //using the current database and adds each combination to an arraylist of food
    public static void findCombinations(ArrayList<Food[]> combinations, Food[] currCombination, ArrayList<Food> workingFoodList, Nutrition nutrition, int start, int end, int index, int choose){
        if (index == choose){
            combinations.add(currCombination);
            return;
        }
        for (int i = start; i <= end && end - i + 1 >= choose - index; i++){
            currCombination[index] = workingFoodList.get(i);
            findCombinations(combinations, currCombination, workingFoodList, nutrition, i+1, end, index+1, choose);
        }
    }
 
    //This method uses an array of food, and a nutrition object to calculate the excess calories for each
    //and ever food group. It will use getter methods from the Nutrition class to calculate the return value.
    //The number returned represents the number of excess calories in a list of food compared to the nutrition requirements
    public static int calculateTotalExcess(Food[] foodList, Nutrition nutrition){
        int grainCals = (int) (nutrition.getPercentGrains() * 0.01 * nutrition.getTotalCals() * -1);
        int FVCals = (int) (nutrition.getPercentFV() * 0.01 * nutrition.getTotalCals() * -1);
        int proteinCals = (int) (nutrition.getPercentProtein() * 0.01 * nutrition.getTotalCals() * -1);
        int otherCals = (int) (nutrition.getPercentOther() * 0.01 * nutrition.getTotalCals() * -1);

        for (int i = 0; i < foodList.length; i++)
        {
            Nutrition foodNutrition =  foodList[i].getNutrition();
            grainCals += foodNutrition.getPercentGrains() * 0.01 * foodNutrition.getTotalCals();
            FVCals += foodNutrition.getPercentFV() * 0.01 * foodNutrition.getTotalCals();
            proteinCals += foodNutrition.getPercentProtein() * 0.01 * foodNutrition.getTotalCals();
            otherCals += foodNutrition.getPercentOther() * 0.01 * foodNutrition.getTotalCals();
        }

        return grainCals + FVCals + proteinCals + otherCals; 
    }

    //The method calculateShortage uses an array of food, and a nutrition object to calculate if there would
    //any case where a group of food has a shortage. This would occur in the program when the same input 
    //(ex.Adult Female) is ran multiple times over and over so the database will then have a shortage in a certain food group.
    //If a shortage of a food group occurs this method will return an array of the shortage 
    //If there is an excess of a food group this method returns zero (since then there is no shortage)   
    //as calculateTotalExcess deals with the excess of food.
    public static int[] calculateShortage(Food[] foodList, Nutrition nutrition){
        int grainCals = (int) (nutrition.getPercentGrains() * 0.01 * nutrition.getTotalCals() * -1);
        int FVCals = (int) (nutrition.getPercentFV() * 0.01 * nutrition.getTotalCals() * -1);
        int proteinCals = (int) (nutrition.getPercentProtein() * 0.01 * nutrition.getTotalCals() * -1);
        int otherCals = (int) (nutrition.getPercentOther() * 0.01 * nutrition.getTotalCals() * -1);

        for (int i = 0; i < foodList.length; i++){
            Nutrition foodNutrition =  foodList[i].getNutrition();
            grainCals += foodNutrition.getPercentGrains() * 0.01 * foodNutrition.getTotalCals();
            FVCals += foodNutrition.getPercentFV() * 0.01 * foodNutrition.getTotalCals();
            proteinCals += foodNutrition.getPercentProtein() * 0.01 * foodNutrition.getTotalCals();
            otherCals += foodNutrition.getPercentOther() * 0.01 * foodNutrition.getTotalCals();
        } 

        int[] result = {grainCals, FVCals, proteinCals, otherCals};

        return result;
    }

    //This method uses an array of food, and a nutrition object to calculate the shortage calories for each
    //and ever food group. It will use getter methods from the Nutrition class to calculate the return value.
    //The number returned will be the total amount of shortage and its important to note that this return value 
    //will always be a negative number because as stated before it represents how much we are short of a certian
    //amount of calories.
    public static int calculateTotalShortage(Food[] foodList, Nutrition nutrition){
        int calShortage = 0;
        int grainCals = (int) (nutrition.getPercentGrains() * 0.01 * nutrition.getTotalCals() * -1);
        int FVCals = (int) (nutrition.getPercentFV() * 0.01 * nutrition.getTotalCals() * -1);
        int proteinCals = (int) (nutrition.getPercentProtein() * 0.01 * nutrition.getTotalCals() * -1);
        int otherCals = (int) (nutrition.getPercentOther() * 0.01 * nutrition.getTotalCals() * -1);

        for (int i = 0; i < foodList.length; i++){
            Nutrition foodNutrition =  foodList[i].getNutrition();
            grainCals += foodNutrition.getPercentGrains() * 0.01 * foodNutrition.getTotalCals();
            FVCals += foodNutrition.getPercentFV() * 0.01 * foodNutrition.getTotalCals();
            proteinCals += foodNutrition.getPercentProtein() * 0.01 * foodNutrition.getTotalCals();
            otherCals += foodNutrition.getPercentOther() * 0.01 * foodNutrition.getTotalCals();
        }
        
        if (grainCals < 0) {calShortage += grainCals;}
        if (FVCals < 0) {calShortage += FVCals;}
        if (proteinCals < 0) {calShortage += proteinCals;}
        if (otherCals < 0) {calShortage += otherCals;}

        return calShortage;
    }

    //This method takes the arraylist of food arrays and finds the best possible combination.
    //The best combination can still be a shortage but it will have the least amount of shortage
    public static Food[] findBestCombination(ArrayList<Food[]> combinations, Nutrition nutrition){
        Food[] bestCombination = new Food[0];
        for (int a = 0; a < combinations.size(); a++){
            if (bestCombination.length == 0){
                bestCombination = combinations.get(a);
            }
            else if (calculateTotalShortage(combinations.get(a), nutrition) == 0){
                if (calculateTotalShortage(bestCombination, nutrition) == 0){
                    if (calculateTotalExcess(combinations.get(a), nutrition) < calculateTotalExcess(bestCombination, nutrition)){
                        bestCombination = combinations.get(a);
                    }
                }
                else if (calculateTotalShortage(bestCombination, nutrition) < 0){
                    bestCombination = combinations.get(a);
                }
            }
            else if (calculateTotalShortage(combinations.get(a), nutrition) < 0){
                if (calculateTotalShortage(bestCombination, nutrition) < 0){
                    if (calculateTotalShortage(combinations.get(a), nutrition) > calculateTotalShortage(bestCombination, nutrition)){
                        bestCombination = combinations.get(a);
                    }
                }
            }
        }
        return bestCombination;
    }
 
    //Finds the best food combination for each hamper that exists using the current list of food from the database
    public static ArrayList<Food[]> findOrderCombo(ArrayList<Food> workingFoodList, Nutrition[] nutrition) {
        ArrayList<Food[]> bestCombinations = new ArrayList<Food[]>();
        for (int i = 0; i < nutrition.length; i++){
            ArrayList<Food[]> combinations = new ArrayList<Food[]>();
            for (int choose = workingFoodList.size(); choose > 0; choose--){
                Food[] currCombination = new Food[choose];
                findCombinations(combinations, currCombination, workingFoodList, nutrition[i], 0, workingFoodList.size() - 1, 0, choose);
            }
            bestCombinations.add(findBestCombination(combinations, nutrition[i]));
            if (calculateTotalShortage(bestCombinations.get(i), nutrition[i]) < 0){
                return bestCombinations;
            }
            for (int j = 0; j < bestCombinations.get(i).length; j++){
                int z = 0;
                while (!bestCombinations.get(i)[j].getName().equals(workingFoodList.get(z).getName())){
                    z++;
                }
                workingFoodList.remove(z);
            }
        }  
        return bestCombinations;
    }
}