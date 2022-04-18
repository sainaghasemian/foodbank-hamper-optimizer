/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 2.4
@since   1.0
*/
package edu.ucalgary.ensf409;

import java.util.ArrayList;
 
public class Inventory {
 
    //finds all possible food combinations using the current database and adds each combination to an arraylist
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
 
    //Returns the number which represents the number of excess calories in a list of food compared to the nutrition requirements
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

    // returns an array of the shortage, if there is a shortage, for each food group
    //If there is an excess, returns zero
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

    //Same as calculate excess but for shortage instead
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

    //Takes the arraylist of food arrays and finds the best combination. 
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