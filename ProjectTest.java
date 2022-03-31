package edu.ucalgary.ensf409;


import java.beans.Transient;
import java.util.regex.*;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

public class ProjectTest {

    //HAMPER TESTS

    @Test
    public void testHamperConstructor() {

    }

    @Test
    public void testSetFood() 
    {
        Food[] expectedFood = new Food[1];
        Nutrition nutrition = new Nutrition(5, 10, 23, 7, 8);
        food[0] = new Food("10", "Apple", nutrition);

        Hamper hamper = new Hamper(null, null);

        hamper.setFood(expectedFood);
        foundFood = hamper.getFood();
        assertEquals("Method setFood did not return the expected result: ", expectedFood, foundFood);
    }

    @Test 
    public void testGetFood() {
       
    }

    @Test
    public void testGetClients() {
        
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

    @Test
    public void testAddToOrder()
    {

    }

    /*
    Throw Tests
    */
    /*
    Enumeration Method Tests
    */

    //Exceptions
    /*InsufficientInventoryException
    FileNotFoundException
    IllegalArgumentException
    */
    //RequestIO takes from user input and throws IllegalArgumentException when input is a letter, special character 
    // or not a number 

    @Test
    public void TestInvalidRequestIO(){
        
    }
}
