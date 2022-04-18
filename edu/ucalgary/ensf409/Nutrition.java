/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 1.8
@since   1.0
*/
package edu.ucalgary.ensf409;

public class Nutrition {
    private final int TOTAL_CALS;
    private final double PERCENT_GRAINS;
    private final double PERCENT_FV;
    private final double PERCENT_PROTEIN;
    private final double PERCENT_OTHER;

    //Nutrition constructor
    public Nutrition(int totalCals, double percentGrains, double percentFV, double percentProtein, double percentOther) {
        this.TOTAL_CALS = totalCals;
        this.PERCENT_GRAINS = percentGrains;
        this.PERCENT_FV = percentFV;
        this.PERCENT_PROTEIN = percentProtein;
        this.PERCENT_OTHER = percentOther;
    }

    //Returns the double representing total calories
    public int getTotalCals() {
        return this.TOTAL_CALS;
    }

    //Returns the double representing percentage of grains
    public double getPercentGrains() {
        return this.PERCENT_GRAINS;
    }

    //Returns the double representing percentage of fruits & veggies
    public double getPercentFV() {
        return this.PERCENT_FV;
    }

    //Returns the double representing percentage of protein
    public double getPercentProtein() {
        return this.PERCENT_PROTEIN;
    }

    //Returns the double representing percentage other
    public double getPercentOther() {
        return this.PERCENT_OTHER;
    }
}