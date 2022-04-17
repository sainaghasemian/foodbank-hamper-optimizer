package edu.ucalgary.ensf409;

import java.util.ArrayList;
 
public class Inventory {
 
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
 
    public static int calculateTotalExcess(Food[] foodList, Nutrition nutrition){
        // calculate the excess calories in a food list
        int grainCals = (int) (nutrition.getPercentGrains() * 0.01 * nutrition.getTotalCals() * -1);
        int FVCals = (int) (nutrition.getPercentFV() * 0.01 * nutrition.getTotalCals() * -1);
        int proteinCals = (int) (nutrition.getPercentProtein() * 0.01 * nutrition.getTotalCals() * -1);
        int otherCals = (int) (nutrition.getPercentOther() * 0.01 * nutrition.getTotalCals() * -1);

        for (int i = 0; i < foodList.length; i++){

            //need to figure out if any values should be floored or ceilinged\

            Nutrition foodNutrition =  foodList[i].getNutrition();
            grainCals += foodNutrition.getPercentGrains() * 0.01 * foodNutrition.getTotalCals();
            FVCals += foodNutrition.getPercentFV() * 0.01 * foodNutrition.getTotalCals();
            proteinCals += foodNutrition.getPercentProtein() * 0.01 * foodNutrition.getTotalCals();
            otherCals += foodNutrition.getPercentOther() * 0.01 * foodNutrition.getTotalCals();
        }

        return grainCals + FVCals + proteinCals + otherCals; 
    }

    public static void calculateShortage(Food[] foodList, Nutrition nutrition, int grainCals, int FVCals, int proteinCals, int otherCals){
        // calculate the shortage of calories in a food list for each food group
        grainCals = (int) (nutrition.getPercentGrains() * 0.01 * nutrition.getTotalCals() * -1);
        FVCals = (int) (nutrition.getPercentFV() * 0.01 * nutrition.getTotalCals() * -1);
        proteinCals = (int) (nutrition.getPercentProtein() * 0.01 * nutrition.getTotalCals() * -1);
        otherCals = (int) (nutrition.getPercentOther() * 0.01 * nutrition.getTotalCals() * -1);

        for (int i = 0; i < foodList.length; i++){
            Nutrition foodNutrition =  foodList[i].getNutrition();
            grainCals += foodNutrition.getPercentGrains() * 0.01 * foodNutrition.getTotalCals();
            FVCals += foodNutrition.getPercentFV() * 0.01 * foodNutrition.getTotalCals();
            proteinCals += foodNutrition.getPercentProtein() * 0.01 * foodNutrition.getTotalCals();
            otherCals += foodNutrition.getPercentOther() * 0.01 * foodNutrition.getTotalCals();
        } 
    }

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
                //shortage detected
                return bestCombinations;
            }
            // remove everything in bestCombinations.get(i) fro workingFoodList
        }  
        return bestCombinations;
    }
}