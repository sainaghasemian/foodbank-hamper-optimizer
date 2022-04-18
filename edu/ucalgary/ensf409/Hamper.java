/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 1.2
@since   1.0
*/
package edu.ucalgary.ensf409;

class Hamper
{
    private final Client[] CLIENTS;
    private Food[] food;

    //Hamper constructor
    public Hamper(Client[] clients)
    {
        this.CLIENTS = clients;
        this.food = null;
    }

    //Sets the array of Food in the Hamper
    public void setFood(Food[] foodList)
    {
        this.food = foodList;
    }

    //Returns the array of Food in the Hamper
    public Food[] getFood()
    {
        return this.food;
    }

    //Returns the array of Clients associated with the Hamper
    public Client[] getClients()
    {
        return this.CLIENTS;
    }
}