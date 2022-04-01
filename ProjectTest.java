package edu.ucalgary.ensf409;


import java.beans.Transient;
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
        String input = "abc";
        boolean exceptionThrown = false;
        try {
            RequestIO.createOrderFormInput(input); //method throwing exception not constructor 
        }
        catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue("An illegal argument exception was not thrown when invalid input was provided", exceptionThrown);
    }

    //Test for file not found exception
    @Test
    public void testCreateRequestOutput() {
        
    }

    @Test 
    public void testGetOrder()
    {

    }

    @Test
    public void testValidateClientInput()
    {

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
        Nutrition nutrition = new Nutrition(5, 10, 23, 7, 8);
        expectedFood[0] = new Food("10", "Apple", nutrition);
        expectedFood[0] = new Food("10", "Apple", nutrition);
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
        foundClients = hamper.getClients()
        assertEquals("The value of the Client array in hamper did not match the expected result", expectedClients, foundClients);
    }

    //CLIENT TESTS

    @Test
    public void testClientConstructor() {

    }

    @Test 
    public void testGetNutrition() {

    }
    

    @Test
    public void testCalculateNutrition()
    {

    }

    @Test 
    public void testOrder()
    {

    }

    //NUTRITION
    
    @Test
    public void testNutritionConstructor()
    {

    }

    @Test
    public void testGetTotalCals()
    {

    }

    @Test
    public void testGetPercentGrains()
    {

    }

    @Test
    public void testGetPercentFV()
    {

    }

    @Test
    public void testGetPercentProtein()
    {

    }

    @Test
    public void testGetPercentOther()
    {

    }

    //FOOD TESTS

    @Test
    public void testFoodConstructor()
    {

    }

    @Test
    public void testGetItemID()
    {

    }

    @Test
    public void testGetName()
    {

    }

    @Test
    public void testGetNutrition()
    {

    }


    //DATABASE TESTS
    
    @Test
    public void testDatabaseConstructor()
    {

    }

    @Test
    public void testRemoveFoodByID()
    {

    }

    @Test
    public void testGetClientList()
    {

    }

    @Test
    public void testGetFoodList()
    {

    }

    //INVENTORY

    @Test
    public void testFindHamperCombo()
    {

    }

    @Test
    public void testCompleteOrderForm()
    {

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
