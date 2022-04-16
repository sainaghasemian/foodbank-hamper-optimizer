package edu.ucalgary.ensf409;

import java.util.ArrayList;
 
public class Inventory {
 
    public static boolean findCombinations(Food[] currCombination, Food[] bestCombination, ArrayList<Food> workingFoodList, Nutrition nutrition, int start, int end, int index, int choose){
        if (index == choose){
            int currentExcess = calculateTotalExcess(currCombination, nutrition);
            int bestExcess = calculateTotalExcess(bestCombination, nutrition);

            System.out.println(currentExcess);

            if (bestExcess == 0){
                return true;
            }

            if ((currentExcess < 0 && bestExcess < 0 && currentExcess > bestExcess) || 
                (currentExcess >= 0 && bestExcess > 0 && currentExcess < bestExcess)){
                bestCombination = (Food[]) currCombination.clone(); 
            }

            if (bestExcess == 0){
                return true;
            }

            return false ;
        }
        for (int i = start; i <= end && end - i + 1 >= choose - index; i++){
            currCombination[index] = workingFoodList.get(i);
            findCombinations(currCombination, bestCombination, workingFoodList, nutrition, i+1, end, index+1, choose);
        }
        return false;
    }
 
    public static int calculateTotalExcess(Food[] foodList, Nutrition nutrition){
        // calculate the excess calories in a food list. negative means shortage
        int grainCals = (int) (nutrition.getPercentGrains() * 0.01 * nutrition.getTotalCals() * -1);
        int FVCals = (int) (nutrition.getPercentFV() * 0.01 * nutrition.getTotalCals() * -1);
        int proteinCals = (int) (nutrition.getPercentProtein() * 0.01 * nutrition.getTotalCals() * -1);
        int otherCals = (int) (nutrition.getPercentOther() * 0.01 * nutrition.getTotalCals() * -1);

        try{
            int a = foodList.length;
        } catch (NullPointerException e){
            return grainCals + FVCals + proteinCals + otherCals;
        }

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

    public static void calculateExcess(Food[] foodList, Nutrition nutrition, int grainCals, int FVCals, int proteinCals, int otherCals){
        // calculate the excess calories in a food list. negative means shortage
        grainCals = (int) (nutrition.getPercentGrains() * 0.01 * nutrition.getTotalCals() * -1);
        FVCals = (int) (nutrition.getPercentFV() * 0.01 * nutrition.getTotalCals() * -1);
        proteinCals = (int) (nutrition.getPercentProtein() * 0.01 * nutrition.getTotalCals() * -1);
        otherCals = (int) (nutrition.getPercentOther() * 0.01 * nutrition.getTotalCals() * -1);

        for (int i = 0; i < foodList.length; i++){

            //need to figure out if any values should be floored or ceilinged\

            Nutrition foodNutrition =  foodList[i].getNutrition();
            grainCals += foodNutrition.getPercentGrains() * 0.01 * foodNutrition.getTotalCals();
            FVCals += foodNutrition.getPercentFV() * 0.01 * foodNutrition.getTotalCals();
            proteinCals += foodNutrition.getPercentProtein() * 0.01 * foodNutrition.getTotalCals();
            otherCals += foodNutrition.getPercentOther() * 0.01 * foodNutrition.getTotalCals();
        } 
    }
 
    public static ArrayList<Food[]> findOrderCombo(ArrayList<Food> workingFoodList, Nutrition[] nutrition) {
        ArrayList<Food[]> bestCombinations = new ArrayList<Food[]>();
        for (int i = 0; i < nutrition.length; i++){
            Food[] bestCombination = null;
            for (int choose = workingFoodList.size(); choose > 0; choose--){
                Food[] currCombination = new Food[choose];
                findCombinations(currCombination, bestCombination, workingFoodList, nutrition[i], 0, workingFoodList.size() - 1, 0, choose);
            }
            bestCombinations.add(bestCombination);
        }  
        return bestCombinations;
    }
}