/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 1.8
@since   1.0
*/
package edu.ucalgary.ensf409;

public class Client {
    private final String CLIENT_ID;
    private final String TYPE;
    private final Nutrition NUTRITION;

    //Client constructor
    public Client(String clientID, String type, int totalCals, int percentGrains, int percentFV, int percentProtein, int percentOther) {
        this.CLIENT_ID = clientID;
        this.TYPE = type;
        this.NUTRITION = new Nutrition(totalCals, percentGrains, percentFV, percentProtein, percentOther);
        
    }

    //Returns Nutrition object
    public Nutrition getNutrition() {
        return this.NUTRITION;
    }
}

