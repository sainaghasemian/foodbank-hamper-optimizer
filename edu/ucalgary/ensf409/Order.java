package edu.ucalgary.ensf409;

import java.util.*;

class Order
{
    private ArrayList<Hamper> hampers;

    //Order constructor
    public Order()
    {
        this.hampers = new ArrayList<Hamper>();
    }

    //Adds a hamper to the ArrayList of Hampers in this Order
    public void addToOrder(Hamper hamper)
    {
        this.hampers.add(hamper);
    }

    //Calculates the total nutrition requirements for each Hamper in this Order and stores 
    //the results in an array of type Nutrition
    public Nutrition[] calculateNutrition()
    {
        Nutrition[] totalNutrition = new Nutrition[this.hampers.size()];

        int i = 0;
        for(Hamper hamper : this.hampers)
        {
            int totalCals = 0;
            double grainsCals = 0;
            double fVCals = 0;
            double proteinCals = 0;
            double otherCals = 0;
            
            for(Client client : hamper.getClients())
            {
                Nutrition currentNutrition = client.getNutrition();
                double currentCals = currentNutrition.getTotalCals();
                totalCals += currentCals;
                grainsCals += currentCals * currentNutrition.getPercentGrains() * 0.01;
                fVCals += currentCals * currentNutrition.getPercentFV() * 0.01;
                proteinCals += currentCals * currentNutrition.getPercentProtein() * 0.01;
                otherCals += currentCals * currentNutrition.getPercentOther() * 0.01;
            }
            
            grainsCals = Math.ceil(grainsCals);
            fVCals = Math.ceil(fVCals);
            proteinCals = Math.ceil(proteinCals);
            otherCals = Math.ceil(otherCals);
            totalCals = (int) (grainsCals + fVCals + proteinCals + otherCals);

            double percentGrains = (grainsCals / totalCals * 100);
            double percentFV = (fVCals / totalCals * 100);
            double percentProtein = (proteinCals / totalCals * 100);
            double percentOther = (otherCals / totalCals * 100);

            totalNutrition[i] = new Nutrition(totalCals, percentGrains, percentFV, percentProtein, percentOther);
            i++;
        }

        return totalNutrition;
    }

    //Returns ArrayList of Hampers from this Order
    public ArrayList<Hamper> getHampers()
    {
        return this.hampers;
    }
}