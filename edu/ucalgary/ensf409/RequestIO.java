/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 2.1
@since   1.0
*/
package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.awt.FlowLayout;
import java.io.File;  

public class RequestIO extends JFrame implements ActionListener, MouseListener{

    private static Order order;
    private String clients;
    private static ArrayList<Food[]> orderForm;
    private static Database workingDB;

    private JLabel instructions;
    private JLabel clientsLabel;
    private JTextArea clientsInput;
    private JButton processOrder;
    private JButton addHamper;
    //private static Writer outputFile;
    
    public RequestIO(){
        super("Create a Hamper");
        setupGUI();
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
    }
    
    //sets up buttons and interface
    public void setupGUI(){
        
        instructions = new JLabel("Please enter the list of clients that are part of a single hamper.");
        clientsLabel = new JLabel("List of clients:");
        
        clientsInput = new JTextArea("e.g.\nAdult Female\nAdult Male\nChild under 8", 5, 15); 
        clientsInput.addMouseListener(this);
        
        addHamper = new JButton("Add Hamper To Order");
        addHamper.addActionListener(this);

        processOrder = new JButton("Process Order");
        processOrder.addActionListener(this);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        
        headerPanel.add(instructions);
        clientPanel.add(clientsLabel);
        clientPanel.add(clientsInput);
        submitPanel.add(addHamper);
        submitPanel.add(processOrder);
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.SOUTH);
    }

    //takes user input string and takes each individual client, finds that client in the arraylist of clients stored in the database class and creates a new client using that
    //The resulting client array is returned
    public Client[] createClientList(String clients){
        ArrayList<Client> newClientList = new ArrayList<Client>();
        Client[] newClientArray;
        int i;

        StringTokenizer tokenizer = new StringTokenizer(clients, "\n");

        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            i = 0;
            while (!token.equals(workingDB.getClientList().get(i).getType())){
                i++;
            }
            newClientList.add(workingDB.getClientList().get(i));
        }

        newClientArray = new Client[newClientList.size()];

        for (i = 0; i < newClientArray.length; i++){
            newClientArray[i] = newClientList.get(i);
        }
        
        return newClientArray;
    }
    
    //Part of the GUI that detects button clicks and runs a process depending on the source
    //First process is adding a hamper to list of hampers in the order
    //Second process is processing the order
    public void actionPerformed(ActionEvent event){
        
        clients = clientsInput.getText();

        if(event.getSource().equals(addHamper)){

            if (validateClientInput(clients)){
                if (this.order == null){
                    order = new Order();
                    order.addToOrder(new Hamper(createClientList(clients)));
                }
                else{
                    order.addToOrder(new Hamper(createClientList(clients)));

                }
                JOptionPane.showMessageDialog(this, "New Hamper is:\n" + clients);
            }
        }
        else if (event.getSource().equals(processOrder)){
            String printMessage = "";
            int j = 1;
            for (Hamper hamper : order.getHampers()){
                printMessage += "\nHamper " + j + ": " + "\n";
                for (Client client : hamper.getClients()){
                    printMessage += client.getType() + "\n";
                }
                j++;
            }
            JOptionPane.showMessageDialog(this, "Hampers To Be Processed:\n" + printMessage);
            Nutrition[] nutrition = order.calculateNutrition();
            orderForm = Inventory.findOrderCombo(workingDB.getFoodList(), nutrition);
            printMessage = "";
            int[] array = new int[4];
            for (int i = 0; i < orderForm.size(); i++){
                if (Inventory.calculateTotalShortage(orderForm.get(i), nutrition[i]) < 0){
                    array = Inventory.calculateShortage(orderForm.get(i), nutrition[i]);
                    printMessage += "Calorie shortages:\nHamper " + (i+1) + ":\n";
                    if (array[0] < 0) printMessage += "Grains: " + (array[0]*-1) + " kcal\n";
                    if (array[1] < 0) printMessage += "Fruits and vegetables: " + (array[1]*-1) + " kcal\n";
                    if (array[2] < 0) printMessage += "Proteins: " + (array[2]*-1) + " kcal\n";
                    if (array[3] < 0) printMessage += "Others: " + (array[3]*-1) + " kcal\n";
                    JOptionPane.showMessageDialog(this, "Order could not be fulfilled due to insufficicent inventory levels.\n" + printMessage);
                    this.order = null;
                    return;
                }
            }
            try{
                createRequestOutput(orderForm, "orderform.txt");
            } catch (FileNotFoundException e){
                JOptionPane.showMessageDialog(this, "Order has been processed, but order form file 'orderform.txt' could not be created.\nThe current order has been erased.");
                return;
            }
            JOptionPane.showMessageDialog(this, "Order has been processed.\nA comprehensive order form file 'orderform.txt' has been created in the working directory.\nThe current order has been erased.");
            this.order = null;

            workingDB.initializeConnection();

            for(Food[] foodArray : orderForm)
            {
                for(Food food : foodArray)
                {
                    workingDB.removeFoodByName(food.getName());
                    //System.out.println("Food should be deleted");
                }
            }

            workingDB.close();
        }
    }
    
    public void mouseClicked(MouseEvent event){
        clientsInput.setText("");     
    }
    
    public void mouseEntered(MouseEvent event){}

    public void mouseExited(MouseEvent event){}

    public void mousePressed(MouseEvent event){}

    public void mouseReleased(MouseEvent event){}  
    
    //Uses enums to validate format and client type of the users input and returns a boolean value
    private boolean validateClientInput(String clients){
        StringTokenizer tokenizer = new StringTokenizer(clients, "\n");
        boolean inputValid = true;

        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if (!token.equals(ClientTypes.ADULTFEMALE.toString()) && 
            !token.equals(ClientTypes.ADULTMALE.toString()) && 
            !token.equals(ClientTypes.CHILDOVER8.toString()) &&
            !token.equals(ClientTypes.CHILDUNDER8.toString())){
                inputValid = false;
                JOptionPane.showMessageDialog(this, token + " is an invalid client type.");
                JOptionPane.showMessageDialog(this, "The valid client types are 'Adult Female', 'Adult Male', 'Child over 8', and 'Child under 8'." +
                "\nRefer to this example for the required input format:" + "\nAdult Female\nAdult Male\nChild under 8");
            }
        }
        
        return inputValid;
    }

    //Takes the orderform and creates the output file
    public static void createRequestOutput(ArrayList<Food[]> foodList, String outputFile) throws FileNotFoundException 
    {
        PrintWriter outputWrite = new PrintWriter(new File(outputFile));
        outputWrite.println("Hamper Order Form\n");
        outputWrite.println("Name: ");
        outputWrite.println("Date: \n");
        outputWrite.println("Original Request");
        for (int k = 0; k < order.getHampers().size(); k++){
            outputWrite.print("Hamper " + (k+1) + ": ");
            for (int l = 0; l < order.getHampers().get(k).getClients().length; l++){
                outputWrite.print(order.getHampers().get(k).getClients()[l].getType());
                if (l + 1 != order.getHampers().get(k).getClients().length){
                    outputWrite.print(", ");
                }
            }
            outputWrite.println();
        }

        outputWrite.println();

        for (int k = 0; k < foodList.size(); k++){
            outputWrite.print("Hamper " + (k+1) + " Items: ");
            for (int l = 0; l < foodList.get(k).length; l++){
                outputWrite.printf("\n%-8s%-50s", foodList.get(k)[l].getItemID(), foodList.get(k)[l].getName());
            }
            outputWrite.println("\n");
        }
        outputWrite.close();
    }

    //Returns the Order
    public Order getOrder() {return this.order;}
    
    public static void main(String[] args) 
    {
        workingDB = new Database("jdbc:mysql://localhost/food_inventory","student","ensf");
        EventQueue.invokeLater(() -> {
            new RequestIO().setVisible(true);        
        });

        
    }
        
}