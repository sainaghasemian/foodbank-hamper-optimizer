/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemiuex<a 
@version 1.8
@since   1.0
*/
package edu.ucalgary.ensf409;


import java.beans.Transient;
import java.io.FileNotFoundException;
import java.util.regex.*;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

public class ProjectTest {

    //REQUESTIO TESTS
    //RequestIO takes from user input and throws IllegalArgumentException when input is a letter, special character 
    // or not a number 

    @Test
    public void testInvalidRequestIO() {
        String[][] input = new String[1];
        input[0][0] = "abc";
        boolean exceptionThrown = false;
        try {
            RequestIO.createOrderFromInput(input); //method throwing exception not constructor 
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue("An illegal argument exception was not thrown when invalid input was provided", exceptionThrown);
    }

    //Test for file not found exception
    @Test
    public void testCreateRequestOutput() {
        boolean exceptionThrown = false;
        try {
            RequestIO.createRequestOutput();
        }
        catch (FileNotFoundException e) {
            exceptionThrown = true;
        }
        assertTrue("A File not found exception was not thrown when createRequestOutput was called", exceptionThrown);
    }

    @Test 
    public void testSetAndGetOrder()
    {
        Order expectedOrder = new Order();
        ArrayList<Hamper> hamperList = new ArrayList<Hamper>();
        //create food and client list so that we can create a hamper to add to out hamper arraylist
        ArrayList<Client> clientList = new ArrayList<Client>();
        ArrayList<Food> foodList = new ArrayList<Food>();
        // saina now we just have to create hamper from the food and client list to add to the hamper list i'm pretty sure ?
        hamperList.setOrder(hamperList);
        foundOrder = order.getOrder();
        assertEquals("The value of the order did not match the expected result ", expectedOrder, foundOrder);
    }

    @Test
    public void testValidateClientInput()
    {
        String clientType = "abc"; // Ana needs help with this, I'm really not sure how to do this but i can when i get back 

    }

    //ORDER TESTS

    @Test
    public void testOrderConstructor()
    {
        Order order = new Order();
        assertNotNull("The default Hamper constructor did not create an object when called with no arguments", order);
    }

    @Test
    public void testAddToOrder()
    {
        Client[] clients = new Client[1];
        clients[0] = new Client(2, "ADULTFEMALE", 2000, 25, 25, 25, 25);
        Hamper hamper = new Hamper(clients);
        order.addToOrder(hamper);
        
        assertNotNull("The ArrayList of Hampers is null after creating a new Order and adding a Hamper to it", order.getHampers().get(0));
    }

    //Method calculateNutrition adds the nutrition requirements of each client in the hamper, for each hamper in the Order. It returns an array with one Nutrition object per Hamper in the array.
    @Test
    public void testCalculateNutrition()
    {
        Order order = new Order();
        Client[] clients = new Client[2];
        clients[0] = new Client(2, "ADULTFEMALE", 2000, 25, 25, 25, 25);
        clients[1] = new Client(3, "ADULTMALE", 2000, 25, 25, 25, 25);
        Hamper hamper = new Hamper(clients);
        order.addToOrder(hamper);
        Nutrition[] expectedNutrition = new Nutrition(4000, 25, 25, 25, 25);
        Nutrition[] foundNutrition = order.calculateNutrition(hamper);

        assertEquals("The value of the Nutrition array created by calculateNutrition did not match the expected result ", expectedNutrition, foundNutrition);
    }

    @Test 
    public void testGetHampers()
    {
        Order order = new Order();
        Client[] clients = new Client[2];
        clients[0] = new Client(2, "ADULTFEMALE", 2000, 25, 25, 25, 25);
        clients[1] = new Client(3, "ADULTMALE", 2000, 25, 25, 25, 25);
        Hamper hamper = new Hamper(clients);
        order.addToOrder(hamper);
        ArrayList<Hamper> expectedHampers = new ArrayList<Hamper>();
        expectedHampers.set(0, hamper);
        ArrayList<Hamper> foundHampers = order.getHampers();

        assertEquals("The value of the Hamper arrayList in order did not match the expected result ", expectedHampers, foundHampers);
    }

    //HAMPER TESTS

    @Test
    public void testHamperConstructor() 
    {
        Client[] clients = new Client[1];
        clients[0] = new Client(2, "ADULTFEMALE", 2000, 25, 25, 25, 25);
        Hamper hamper = new Hamper(clients);
        assertNotNull("Hamper constructor did not create an object when given a list of clients", hamper);
    }

    @Test
    public void testSetAndGetFood() 
    {
        Food[] expectedFood = new Food[1];
        expectedFood[0] = new Food("10", "Apple", 5, 10, 23, 7, 8);
        Hamper hamper = new Hamper(null);
        hamper.setFood(expectedFood);
        foundFood = hamper.getFood();
        assertEquals("The value of the Food array in hamper did not match the expected result ", expectedFood, foundFood);
    }

    @Test
    public void testGetClients() 
    {
        Order order = new Order();
        Client[] expectedClients = new Client[1];
        expectedClients[0] = new Client(2, "ADULTFEMALE", 2000, 25, 25, 25, 25);
        Hamper hamper = new Hamper(expectedClients);
        foundClients = hamper.getClients();
        assertEquals("The value of the Client array in Hamper did not match the expected result", expectedClients, foundClients);
    }

    //CLIENT TESTS

    @Test
    public void testClientConstructor() {
        Client client = new Client(2, "ADULTFEMALE", 2000, 25, 25, 25, 25);
        assertNotNull("Client constructor did not create a new object of type Client when given the appropriate arguments", client);
    }

    @Test 
    public void testGetNutritionClient() {
        Client client = new Client(2, "ADULTFEMALE", 2000, 25, 25, 25, 25);
        Nutrition expectedNutrition = new Nutrition(2000, 25, 25, 25, 25);
        Nutrition actualNutrition = client.getNutrition();
        assertEquals("The Nutrition object of Client did not match the expected result", expectedNutrition, actualNutrition);
    }

    //NUTRITION
    
    @Test
    public void testNutritionConstructor()
    {
        Nutrition nutrition = new Nutrition(2000, 25, 25, 25, 25);
        assertNotNull("Nutrition constructor did not create a new object of type Nutrition when given the appropriate arguments", nutrition);
    }

    @Test
    public void testGetTotalCals()
    {
        Nutrition nutrition = new Nutrition(2000, 25, 25, 25, 25);
        int expectedTotalCals = 2000;
        int actualTotalCals = nutrition.getTotalCals();
        assertEquals("The totalCals of Nutrition object did not match the expected result", expectedTotalCals, actualTotalCals);
    }

    @Test
    public void testGetPercentGrains()
    {
        Nutrition nutrition = new Nutrition(2000, 40, 20, 20, 20);
        int expectedPercentGrains = 40;
        int actualPercentGrains = nutrition.getPercentGrains();
        assertEquals("The percentGrains of Nutrition object did not match the expected result", expectedPercentGrains, actualPercentGrains);
    }

    @Test
    public void testGetPercentFV()
    {
        Nutrition nutrition = new Nutrition(2000, 20, 40, 20, 20);
        int expectedPercentFV = 40;
        int actualPercentFV = nutrition.getPercentFV();
        assertEquals("The percentFV of Nutrition object did not match the expected result", expectedPercentFV, actualPercentFV);
    }

    @Test
    public void testGetPercentProtein()
    {
        Nutrition nutrition = new Nutrition(2000, 20, 20, 40, 20);
        int expectedPercentProtein = 40;
        int actualPercentProtein = nutrition.getPercentProtein();
        assertEquals("The percentProtein of Nutrition object did not match the expected result", expectedPercentProtein, actualPercentProtein);
    }

    @Test
    public void testGetPercentOther()
    {
        Nutrition nutrition = new Nutrition(2000, 20, 20, 20, 40);
        int expectedPercentOther = 40;
        int actualPercentOther = nutrition.getPercentOther();
        assertEquals("The percentOther of Nutrition object did not match the expected result", expectedPercentOther, actualPercentOther);
    }

    //FOOD TESTS

    @Test
    public void testFoodConstructor()
    {
        Food food = new Food("10", "Apple", 5, 10, 23, 7, 8);
        assertNotNull("Food constructor did not create a new object of type Food when given the appropriate arguments", food);
    }

    @Test
    public void testGetItemID()
    {
        Food food = new Food("10", "Apple", 5, 10, 23, 7, 8);
        String expectedItemID = "10";
        String actualItemID = food.getItemID();
        assertEquals("The itemID of Food object did not match the expected result", expectedItemID, actualItemID);
    }

    @Test
    public void testGetName()
    {
        Food food = new Food("10", "Apple", 5, 10, 23, 7, 8);
        String expectedName = "Apple";
        String actualName = food.getName();
        assertEquals("The name of Food object did not match the expected result", expectedName, actualName);
    }

    @Test
    public void testGetNutritionFood()
    {
        Food food = new Food("10", "Apple", 5, 10, 23, 7, 8);
        String expectedNutrition = new Nutrition(5, 10, 23, 7, 8);
        String actualNutrition = food.getNutrtition();
        assertEquals("The nutrition of Food object did not match the expected result", expectedNutrition, actualNutrition);
    }


    //DATABASE TESTS
    
    @Test
    public void testDefaultDatabaseConstructor() 
    {
        Database database = new Database();
        assertNotNull("The default Database constructor did not create an object when called with no arguments", database);
    }

    @Test
    public void testDatabaseConstructor() //Ana just did this, needs review
    {
        ArrayList<Client> clientList = new ArrayList<Client>();
        ArrayList<Food> foodList = new ArrayList<Food>();
        Database database = new Database(clientList, foodList);
        assertNotNull("The Database constructor did not create an object when called with client and food array lists", database);
    }

    @Test
    public void testRemoveFoodByID() //Ana just did this, needs review
    {
        Database database = new Database();
        String id = "1738";
        ArrayList<Food> foodList = new ArrayList<Food>();
        //Create a food object with id 1738 and add to list so we can then remove it
        Food food = new Food("1738",  "Apple", 5, 10, 23, 7, 8);
        //All that there's left to do here is add this food object to foodList
        //cause this will remove it:
        database.removeFoodByID(id); 
        boolean check = foodList.contains(id); // let me know if you guys think we can just use .contains
        assertTrue("The method removeFoodByID did not remove a given string id from the food list array, ", check); //would this be assertTrue ?
    }

    @Test
    public void testGetClientList() //Ana not done
    {
        Database database = new Database();
        ArrayList<Client> expectedList = new ArrayList<Client>();
        //Make a new client and add to array list
        ArrayList<Client> actualList = database.getClientList();
        assertEquals("The client list of the database did not match the expected result, ", expectedList, actualList);
    }

    @Test
    public void testGetFoodList() //Ana not done
    {
        Database database = new Database();
        ArrayList<Food> expectedList = new ArrayList<Food>();
        ArrayList<Food> actualList = database.getFoodList();
        assertEquals("The food list of the database did not match the expected result, ", expectedList, actualList);
    }

    //INVENTORY

    @Test
    public void testFindHamperComboException()
    {
        Inventory inventory = new Inventory();
        ArrayList<Food> foodList = new ArrayList<Food>();

        //test if it throws insufficient inventory exception
    }

    @Test
    public void testFindHamperCombo()
    {
        //no idea... maybe check if it returns a valid arraylist of food?
    }

    @Test
    public void testCompleteOrderForm() //Ana not done
    {
        ArrayList<Food> foodList = new ArrayList<Food>();
    }

    //CLIENTTYPES TESTS

    @Test
    public void testClientTypes()
    {
         //ADULFEMALE
        String expected = "Adult Female";
        String actual = Directions.ADULTFEMALE.toString();
        assertEquals("Enumeration Direction toString Method not returning correct String", expected, actual);
        //ADULTMALE
        expected = "Adult Male";
        actual = Directions.ADULTMALE.toString();
        assertEquals("Enumeration Direction toString Method not returning correct String", expected, actual);
        //CHILDOVER8
        expected = "Child Over 8";
        actual = Directions.CHILDOVER8.toString();
        assertEquals("Enumeration Direction toString Method not returning correct String", expected, actual);
        //CHILDUNDER8
        expected = "Child Under 8";
        actual = Directions.CHILDUNDER8.toString();
        assertEquals("Enumeration Direction toString Method not returning correct String", expected, actual);
    }
}
