package edu.ucalgary.ensf409;

import java.util.*;

class Database
{
    private ArrayList<Client> clientList;
    private ArrayList<Food> foodList;

    //Default Database constructor
    public Database()
    {
        clientList = new ArrayList<Client>();
        foodList = new ArrayList<Food>();

        //Access database and fill arraylists
    }

    //Database constructor
    public Database(ArrayList<Client> clientList, ArrayList<Food> foodList)
    {
        this.clientList = clientList;
        this.foodList = foodList;
    }

    public void removeFoodByID(String id)
    {
        //To be implemented later once we figure out database stuff
    }

    public ArrayList<Client> getClientList()
    {
        return this.clientList;
    }

    public ArrayList<Food> getFoodList()
    {
        return this.foodList;
    }
}