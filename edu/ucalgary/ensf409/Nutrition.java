/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 1.8
@since   1.0
*/
package edu.ucalgary.ensf409;

public class Nutrition {
    private final int TOTAL_CALS;
    private final int PERCENT_GRAINS;
    private final int PERCENT_FV;
    private final int PERCENT_PROTEIN;
    private final int PERCENT_OTHER;

    //Nutrition constructor
    public Nutrition(int totalCals, int percentGrains, int percentFV, int percentProtein, int percentOther) {
        this.TOTAL_CALS = totalCals;
        this.PERCENT_GRAINS = percentGrains;
        this.PERCENT_FV = percentFV;
        this.PERCENT_PROTEIN = percentProtein;
        this.PERCENT_OTHER = percentOther;
    }

    //Returns the int representing total calories
    public int getTotalCals() {
        return this.TOTAL_CALS;
    }

    //Returns the int representing percentage of grains
    public int getPercentGrains() {
        return this.PERCENT_GRAINS;
    }

    //Returns the int representing percentage of fruits & veggies
    public int getPercentFV() {
        return this.PERCENT_FV;
    }

    //Returns the int representing percentage of protein
    public int getPercentProtein() {
        return this.PERCENT_PROTEIN;
    }

    //Returns the int representing percentage other
    public int getPercentOther() {
        return this.PERCENT_OTHER;
    }
}