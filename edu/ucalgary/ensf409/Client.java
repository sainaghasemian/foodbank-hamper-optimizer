/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 1.9
@since   1.0
*/
package edu.ucalgary.ensf409;

public class Client {
    //private final String CLIENT_ID;
    private final String TYPE;
    private final Nutrition NUTRITION;

    //Client constructor
    public Client(String type, int percentGrains, int percentFV, int percentProtein, int percentOther, int totalCals) {
        //this.CLIENT_ID = clientID;
        this.TYPE = type;
        this.NUTRITION = new Nutrition(totalCals, percentGrains, percentFV, percentProtein, percentOther);
        
    }

    //Returns Nutrition object
    public Nutrition getNutrition() {
        return this.NUTRITION;
    }

    //Returns type of string
    public String getType(){
        return this.TYPE;
    }
}

