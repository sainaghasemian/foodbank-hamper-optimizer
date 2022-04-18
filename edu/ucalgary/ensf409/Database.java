/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 2.6
@since   1.0
*/
package edu.ucalgary.ensf409;

import java.util.*;
import java.sql.*;

class Database
{
    private ArrayList<Client> clientList;
    private ArrayList<Food> foodList;

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;    
    
    private Connection dbConnect;
    private ResultSet results;

    //Default Database constructor CHANGED FROM UML
    public Database(String url, String user, String pw)
    {
        clientList = new ArrayList<Client>();
        foodList = new ArrayList<Food>();

        // Database URL
        this.DBURL = url;

        //  Database credentials
        this.USERNAME = user;
        this.PASSWORD = pw;

        //Access database and fill arraylists
        initializeConnection();

        try 
        {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM DAILY_CLIENT_NEEDS");
            
            while (results.next())
            {
                Client client = new Client(results.getString("Client"), results.getInt("WholeGrains"), results.getInt("FruitVeggies"), results.getInt("Protein"), results.getInt("Other"), results.getInt("Calories"));
                clientList.add(client);
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try 
        {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM AVAILABLE_FOOD");
            
            int i = 1;
            while (results.next())
            {
                Food food = new Food(i, results.getString("Name"), results.getInt("GrainContent"), results.getInt("FVContent"), results.getInt("ProContent"), results.getInt("Other"), results.getInt("Calories"));
                foodList.add(food);
                i++;
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        close();
    }

    //Database constructor - for testing pruposes
    public Database(ArrayList<Client> clientList, ArrayList<Food> foodList)
    {
        this.clientList = clientList;
        this.foodList = foodList;
        
        // Setting these to null since they are not needed for testing
        this.DBURL = null;
        this.USERNAME = null;
        this.PASSWORD = null;
    }

    public void initializeConnection()
    {
        try
        {
            dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }  
    }

    public void close() 
    {
        try 
        {
            results.close();
            dbConnect.close();
        } catch (SQLException e) 
        {
        e.printStackTrace();
        }
    }

    public void removeFoodByName(String name)
    {
        try 
        {
            String query = "DELETE FROM available_food WHERE Name = ? LIMIT 1";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, name);
            
            myStmt.executeUpdate();
            
            myStmt.close();

        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
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