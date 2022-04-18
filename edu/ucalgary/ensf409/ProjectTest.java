/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
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
import java.util.*;

public class ProjectTest {

    

    //ORDER CLASS TESTS

    //testOrderConstructor() tests to see if the default Hamper constructor creates a non-null object when given appropriate arguments.

    @Test
    public void testOrderConstructor()
    {
        Order order = new Order();
        assertNotNull("The default Hamper constructor did not create an object when called with no arguments", order);
    }

    //testAddToOrder() will first create an array of clients, the client array will be put into the hamper
    //this will test if the array list in hamper is not nullo when the client creates a new order
    
    @Test
    public void testAddToOrder()
    {
        Client[] clients = new Client[1];
        clients[0] = new Client("Adult Female", 2000, 25, 25, 25, 25);
        Hamper hamper = new Hamper(clients);
        Order order = new Order();
        order.addToOrder(hamper);
        
        assertNotNull("The ArrayList of Hampers is null after creating a new Order and adding a Hamper to it", order.getHampers().get(0));
    }

    //testCalculateNutrition() tests to see if the value of array Nutrition matches the expected results.
    //Method calculateNutrition adds the nutrition requirements of each client in the hamper, for each hamper in the Order. 
    //It returns an array with one Nutrition object per Hamper in the array.

    @Test
    public void testCalculateNutrition()
    {
        Order order = new Order();
        Client[] clients = new Client[2];
        clients[0] = new Client("Adult Female", 25, 25, 25, 25, 2000);
        clients[1] = new Client("Adult Male", 25, 25, 25, 25, 2000);
        Hamper hamper = new Hamper(clients);
        order.addToOrder(hamper);
        Nutrition[] expectedNutrition = new Nutrition[1];
        expectedNutrition[0] = new Nutrition(4000, 25, 25, 25, 25);
        Nutrition[] foundNutrition = order.calculateNutrition();

        boolean nutritionMatch = true;
        for(int i = 0; i < expectedNutrition.length; i++)
        {
            if(expectedNutrition[i].getPercentGrains() != foundNutrition[i].getPercentGrains())
            {
                System.out.println(expectedNutrition[i].getPercentGrains() + " vs " +  foundNutrition[i].getPercentGrains());
                nutritionMatch  = false;
            }
            else if(expectedNutrition[i].getPercentFV() != foundNutrition[i].getPercentFV())
            {
                nutritionMatch  = false;
            }
            else if(expectedNutrition[i].getPercentProtein() != foundNutrition[i].getPercentProtein())
            {
                nutritionMatch  = false;
            }
            else if(expectedNutrition[i].getPercentOther() != foundNutrition[i].getPercentOther())
            {
                nutritionMatch  = false;
            }
        }

        assertTrue("The value of the Nutrition array created by calculateNutrition did not match the expected result ", nutritionMatch);
    }

    //testGetHampers() creates valid clients inside of a client array to be added to a hamper. These are then added to the order, and the method
    //getHampers() is ultimately tested with the order object to verify that it correctly returned the array list of hampers

    @Test 
    public void testGetHampers()
    {
        Order order = new Order();
        Client[] clients = new Client[2];
        clients[0] = new Client("Adult Female", 2000, 25, 25, 25, 25);
        clients[1] = new Client("Adult Male", 2000, 25, 25, 25, 25);
        Hamper hamper = new Hamper(clients);
        order.addToOrder(hamper);
        ArrayList<Hamper> expectedHampers = new ArrayList<Hamper>();
        expectedHampers.add(0, hamper);
        ArrayList<Hamper> foundHampers = order.getHampers();

        assertEquals("The value of the Hamper arrayList in order did not match the expected result ", expectedHampers, foundHampers);
    }

    //HAMPER CLASS TESTS

    //testHamperConstructor() tests to see if the Hamper constructor creates a non-null object when given appropriate arguments.

    @Test
    public void testHamperConstructor() 
    {
        Client[] clients = new Client[1];
        clients[0] = new Client("Adult Female", 2000, 25, 25, 25, 25);
        Hamper hamper = new Hamper(clients);
        assertNotNull("Hamper constructor did not create an object when given a list of clients", hamper);
    }

    //testSetandGetFood() tests the setFood() method for a Hamper object by setting the food field of the object,
    //then using getFood() method to get the object's food field. Then compares this return to the expected return object.

    @Test
    public void testSetAndGetFood() 
    {
        Food[] expectedFood = new Food[1];
        expectedFood[0] = new Food(10, "Apple", 5, 10, 23, 7, 8);
        Hamper hamper = new Hamper(null);
        hamper.setFood(expectedFood);
        Food[] foundFood = hamper.getFood();

        boolean foodsMatch = true;
        int i = 0;
        for(Food food : expectedFood)
        {
            if(food != foundFood[i])
            {
                foodsMatch  = false;
            }

            i++;
        }

        assertTrue("The value of the Food array in hamper did not match the expected result ", foodsMatch);
    }

    //testGetClients() tests the getClients() method for an Order object. Constructs an Order object using a Client object, 
    //then gets the clients field getter. Then compares this return to the expected result.

    @Test
    public void testGetClients() 
    {
        Client[] expectedClients = new Client[1];
        expectedClients[0] = new Client("Adult Female", 2000, 25, 25, 25, 25);
        Hamper hamper = new Hamper(expectedClients);
        Client[] foundClients = hamper.getClients();
        boolean clientsMatch = true;
        int i = 0;
        for(Client expectedClient : expectedClients)
        {
            if(expectedClient != foundClients[i])
            {
                clientsMatch  = false;
            }

            i++;
        }
        assertTrue("The value of the Client array in Hamper did not match the expected result", clientsMatch);
    }

    //CLIENT CLASS TESTS

    //testClientConstructor() tests to see if the Client constructor creates a non-null object when given appropriate arguments.

    @Test
    public void testClientConstructor() {
        Client client = new Client("Adult Female", 2000, 25, 25, 25, 25);
        assertNotNull("Client constructor did not create a new object of type Client when given the appropriate arguments", client);
    }

    //testGetNutritionClient() tests the getNutrition() method for a Client object. Constructs a Client object using appropriate
    //arguments, then compares this against the expected Nutrition object.

    @Test 
    public void testGetNutritionClient() {
        Nutrition expectedNutrition = new Nutrition(2000, 25, 25, 25, 25);
        Client client = new Client("Adult Female", expectedNutrition);
        Nutrition actualNutrition = client.getNutrition();
        assertEquals("The Nutrition object of Client did not match the expected result", expectedNutrition, actualNutrition);
    }

    //NUTRITION CLASS TESTS

    //testNutritionConstructor() tests to see if Nutrition constructor creates a non-null object when given appropriate arguments.
    
    @Test
    public void testNutritionConstructor()
    {
        Nutrition nutrition = new Nutrition(2000, 25, 25, 25, 25);
        assertNotNull("Nutrition constructor did not create a new object of type Nutrition when given the appropriate arguments", nutrition);
    }

    //testGetTotalCals() tests the getTotalCals() method for a Nutrition object. Constructs a Nutrition object using appropriate arguments
    //then uses the totalCals field getter, and compares this against the expected totalCals.

    @Test
    public void testGetTotalCals()
    {
        Nutrition nutrition = new Nutrition(2000, 25, 25, 25, 25);
        int expectedTotalCals = 2000;
        int actualTotalCals = nutrition.getTotalCals();
        assertEquals("The totalCals of Nutrition object did not match the expected result", expectedTotalCals, actualTotalCals);
    }

    //testGetPercentGrains() tests the getPercentGrains() method for a Nutrition object. Constructs a Nutrition object using appropriate arguments
    //then uses the percentGrains field getter, and compares this against the expected percentGrains.

    @Test
    public void testGetPercentGrains()
    {
        Nutrition nutrition = new Nutrition(2000, 40, 20, 20, 20);
        double expectedPercentGrains = 40;
        double actualPercentGrains = nutrition.getPercentGrains();
        assertEquals("The percentGrains of Nutrition object did not match the expected result", expectedPercentGrains, actualPercentGrains, 0.001);
    }

    //testGetPercentFV() tests the getpercentFV() method for a Nutrition object. Constructs a Nutrition object using appropriate arguments
    //then uses the percentFV field getter, and compares this against the expected percentFV.

    @Test
    public void testGetPercentFV()
    {
        Nutrition nutrition = new Nutrition(2000, 20, 40, 20, 20);
        double expectedPercentFV = 40;
        double actualPercentFV = nutrition.getPercentFV();
        assertEquals("The percentFV of Nutrition object did not match the expected result", expectedPercentFV, actualPercentFV, 0.001);
    }

    //testGetPercentProtein() tests the getpercentProtein() method for a Nutrition object. Constructs a Nutrition object using appropriate arguments
    //then uses the percentProtein field getter, and compares this against the expected percentProtein.

    @Test
    public void testGetPercentProtein()
    {
        Nutrition nutrition = new Nutrition(2000, 20, 20, 40, 20);
        double expectedPercentProtein = 40;
        double actualPercentProtein = nutrition.getPercentProtein();
        assertEquals("The percentProtein of Nutrition object did not match the expected result", expectedPercentProtein, actualPercentProtein, 0.001);
    }

    //testGetPercentOther() tests the getpercentOther() method for a Nutrition object. Constructs a Nutrition object using appropriate arguments
    //then uses the percentOther field getter, and compares this against the expected percentOther.

    @Test
    public void testGetPercentOther()
    {
        Nutrition nutrition = new Nutrition(2000, 20, 20, 20, 40);
        double expectedPercentOther = 40;
        double actualPercentOther = nutrition.getPercentOther();
        assertEquals("The percentOther of Nutrition object did not match the expected result", expectedPercentOther, actualPercentOther, 0.001);
    }

    //FOOD CLASS TESTS

    //testFoodConstructor() tests to see if Food constructor creates a non-null object when given the appropriate arguments.

    @Test
    public void testFoodConstructor()
    {
        Food food = new Food(1, "Apple", 5, 10, 23, 7, 8);
        assertNotNull("Food constructor did not create a new object of type Food when given the appropriate arguments", food);
    }

    //testGetItemID() tests the getItemID() method for a Food object. Constructs a Food object using appropriate arguments,
    //then uses the itemID field getter, and compares this against the expected itemID.

    @Test
    public void testGetItemID()
    {
        Food food = new Food(8, "Apple", 5, 10, 23, 7, 8);
        int expectedItemID = 8;
        int actualItemID = food.getItemID();
        assertEquals("The itemID of Food object did not match the expected result", expectedItemID, actualItemID);
    }

    //testGetName() tests the getName() method for a Food object. Constructs a Food object using appropriate arguments,
    //then uses the name field getter, and compares this against the expected name.

    @Test
    public void testGetName()
    {
        Food food = new Food(13, "Apple", 5, 10, 23, 7, 8);
        String expectedName = "Apple";
        String actualName = food.getName();
        assertEquals("The name of Food object did not match the expected result", expectedName, actualName);
    }

    //testGetNutrition() tests the getNutrition() method for a Food object. Constructs a Food object using appropriate arguments,
    //then uses the nutrition field getter, and compares this against the expected nutrition.

    @Test
    public void testGetNutritionFood()
    {
        Nutrition expectedNutrition = new Nutrition(5, 10, 23, 7, 8);
        Food food = new Food(10, "Apple", expectedNutrition);
        Nutrition actualNutrition = food.getNutrition();
        assertEquals("The nutrition of Food object did not match the expected result", expectedNutrition, actualNutrition);
    }

    //DATABASE CLASS TESTS

    //The database constructors and methods other that testGetClientList and testGetFoodList cannot be tested
    //because they must make connections to the database.

    //tesGetClientList() tests the getClientList() method for a Databse object, by creating a client array list with a valid
    //client object and calling the method to verify if it belongs to the database 

    @Test
    public void testGetClientList()
    {
        ArrayList<Client> expectedClientList = new ArrayList<Client>();
        Client client = new Client("Adult Female", 2000, 25, 25, 25, 25);
        expectedClientList.add(client);
        ArrayList<Food> foodList = new ArrayList<Food>();
        Food food = new Food(10, "Apple", 5, 10, 23, 7, 8);
        foodList.add(food);
        Database database = new Database(expectedClientList, foodList);
        ArrayList<Client> actualList = database.getClientList();
        assertEquals("The client list of the database did not match the expected result, ", expectedClientList, actualList);
    }

    //tesGetFoodList() tests the getFoodList() method for a Databse object, by creating a food array list with a valid
    //food object and calling the method to verify if it belongs to the database

    @Test
    public void testGetFoodList()
    {
        ArrayList<Client> clientList = new ArrayList<Client>();
        Client client = new Client("Adult Female", 2000, 25, 25, 25, 25);
        clientList.add(client);
        ArrayList<Food> expectedFoodList = new ArrayList<Food>();
        Food food = new Food(10, "Apple", 5, 10, 23, 7, 8);
        expectedFoodList.add(food);
        Database database = new Database(clientList, expectedFoodList);
        ArrayList<Food> actualList = database.getFoodList();
        assertEquals("The client list of the database did not match the expected result, ", expectedFoodList, actualList);
    }

    //INVENTORY CLASS TESTS

    
    //Test if the Food list generated by the method findHamperCombo contains enough total calories to satisfy the order requirements.

    //DELETE LATER
    /*
    @Test
    public void testFindHamperCombo()
    {

        ArrayList<Food> foodList = new ArrayList<Food>();
        Food apple = new Food("10", "Apple", 100, 25, 25, 25, 25);
        Food chocolate = new Food("14", "Chocolate", 1300, 25, 25, 25, 25);
        foodList.add(apple);
        foodList.add(chocolate);

        Database database = new Database(null, foodList);

        Nutrition[] nutritionList = new Nutrition[1];
        Nutrition nutrition = new Nutrition(1200, 25, 25, 25, 25);
        nutritionList.add(nutrition);

        ArrayList<Food> foundFoodList = Inventory.findHamperCombo(nutritionList);

        int expectedMinTotalCals = 1200;
        int foundTotalCals = 0;

        for(Food food : foundFoodList)
        {
            foundTotalCals += food.getTotalCals();
        }

        assertTrue("The total calories in the Food list returned by the method findHamperCombo is less than the required total calories in the order", expectedMinTotalCals >= foundTotalCals);
    }
    */

    @Test
    public void testFindCombinations() //Ana
    {

    }

    @Test
    public void testCalculateTotalExcess() //Saina
    {
        Nutrition expectedNutrition = new Nutrition(1000, 25, 25, 25, 25);
        Food[] expectedFood = new Food[2];
        expectedFood[0] = new Food(10, "Burger", 25, 25, 25, 25, 1000);
        expectedFood[1] = new Food(20, "Pizza", 30, 20, 25, 25, 500);
        int expectedExcess = 500;
        int actualExcess = Inventory.calculateTotalExcess(expectedFood, expectedNutrition);
        assertEquals("The expected total excess of the inventory did not match the expected result, ", expectedExcess, actualExcess);
    }

    @Test
    public void testCalculateShortage() //Saina
    {

    }

    @Test
    public void testCalculateTotalShortage() //Ana
    {
        Nutrition expectedNutrition = new Nutrition(1000, 25, 25, 25, 25);
        Food[] expectedFood = new Food[2];
        expectedFood[0] = new Food(10, "Banana", 25, 25, 25, 25, 100);
        expectedFood[1] = new Food(20, "Croissant", 30, 20, 25, 25, 400);
        int expectedShortage = -500;
        int actualShortage = Inventory.calculateTotalShortage(expectedFood, expectedNutrition);
        assertEquals("The expected total excess of the inventory did not match the expected result, ", expectedShortage, actualShortage);
    }

    @Test
    public void testFindBestCombination() //Rachel
    {

    }

    //To test the method completeOrderForm, we initialize a Database object with the a list of food.
    //completeOrderForm should take an arrayList of Food objects generated by findHamperCombo and adjust the inventory accordingly.
    //If we suppose that the order contains one hamper and the Food list in the Hamper has the same contents as the Food list in the Database class,
    //after completeOrderForm is called, the Food list in the Database class should be empty. 
/*
    @Test
    public void testFindOrderCombo() //Rachel
    {
        ArrayList<Food> foodList = new ArrayList<Food>();
        Food apple = new Food("10", "Apple", 100, 25, 25, 25, 25);
        Food chocolate = new Food("14", "Chocolate", 1300, 25, 25, 25, 25);
        foodList.add(apple);
        foodList.add(chocolate);
        Database database = new Database(null, foodList);
        Inventory.completeOrderForm(foodList);
        ArrayList<Food> foundFoodList = database.getFoodList();

        boolean isFoodListEmpty = false;
        if(foundFoodList.size() == 0)
        {
            isFoodListEmpty = true;
        }

        assertTrue("The Food list in Database is not empty after adjusting the inventory", isFoodListEmpty);
    }*/

    //CLIENTTYPES ENUMERATION TESTS

    //Any ClientTypes can be expanded to its written form 
    //for example (ADULTMALE -> Adult Male) using the toString method

    @Test
    public void testClientTypes()
    {
         //Adult Female
        String expected = "Adult Female";
        String actual = ClientTypes.ADULTFEMALE.toString();
        assertEquals("Enumeration Direction toString Method not returning correct String", expected, actual);
        //Adult Male
        expected = "Adult Male";
        actual = ClientTypes.ADULTMALE.toString();
        assertEquals("Enumeration Direction toString Method not returning correct String", expected, actual);
        //Child Over 8
        expected = "Child over 8";
        actual = ClientTypes.CHILDOVER8.toString();
        assertEquals("Enumeration Direction toString Method not returning correct String", expected, actual);
        //Child under 8
        expected = "Child under 8";
        actual = ClientTypes.CHILDUNDER8.toString();
        assertEquals("Enumeration Direction toString Method not returning correct String", expected, actual);
    }

    //REQUESTIO CLASS TESTS

    //test for file not found exception

    @Test
    public void testCreateRequestOutput() {
        boolean exceptionThrown = false;
        try {
            ArrayList<Food[]> foodList = new ArrayList<Food[]>();
            RequestIO.createRequestOutput(foodList, "order");
        }
        catch (FileNotFoundException e) {
            exceptionThrown = true;
        }
        assertFalse("A File not found exception was thrown when createRequestOutput was called", exceptionThrown);
    }

    //testSetandGetOrder() tests the setOrder() method for a Hamper list by setting the order of the hamper list
    //then using getOrder() method to get the object's order field. Then compares this return to the expected return object.

    @Test 
    public void testSetAndGetOrder()
    {
        Order expectedOrder = new Order();
        RequestIO requestIO = new RequestIO(expectedOrder);
        Order foundOrder = requestIO.getOrder();
        assertEquals("The value of the order did not match the expected result ", expectedOrder, foundOrder);
    }

    //testValidateClientInputForIncorrectType() tests the validateClientInput() method, focusing on the client type string 
    //it is provided with an invalid client type, to check whether or not it correctly returned the expected output of false   

    //We were forced to comment out this test since validateClientType produces a GUI. 
    /*
    @Test
    public void testValidateClientInputForIncorrectType()
    {
        String clientType = "Male Child Under 8";
        boolean expectedValidate = false;
        RequestIO requestIO = new RequestIO(clientType);
        boolean actualValidate = requestIO.validateClientInput(clientType);
        assertEquals("Validate method for client input failed to return false for an invalid input", expectedValidate, actualValidate);

    }
    */
}
