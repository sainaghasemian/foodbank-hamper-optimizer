/**
@author Saina Ghasemian-Roudsari, Rachel Dalton, Ana Perrone, Isaiah Lemieux<a 
@version 1.9
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

public class RequestIO extends JFrame implements ActionListener, MouseListener{

    private static Order order;
    private String clients;
    private static ArrayList<Food[]> orderForm;
    private Database workingDB;

    private JLabel instructions;
    private JLabel clientsLabel;
    private JTextArea clientsInput;
    private JButton processOrder;
    private JButton addHamper;
    private static Writer outputFile;
    
    public RequestIO(){
        super("Create a Hamper");
        setupGUI();
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
    }
    
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
    
    public void actionPerformed(ActionEvent event){
        workingDB = new Database("jdbc:mysql://localhost/food_inventory","student","ensf");
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
            //createRequestOutput("orderform.txt");
            printMessage = "";
            int grain = 0, FV = 0, protein = 0, other = 0;
            for (int i = 0; i < orderForm.size(); i++){
                if (Inventory.calculateTotalShortage(orderForm.get(i), nutrition[i]) < 0){
                    Inventory.calculateShortage(orderForm.get(i), nutrition[i], grain, FV, protein, other);
                    printMessage += "Calorie shortages:\n";
                    if (grain < 0) printMessage += "Grains: " + (grain*-1) + "\n";
                    if (FV < 0) printMessage += "Fruits and vegetables: " + (FV*-1) + "\n";
                    if (protein < 0) printMessage += "Proteins: " + (protein*-1) + "\n";
                    if (other < 0) printMessage += "Others: " + (other*-1) + "\n";
                    this.order = null;
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Order has been processed.\nA comprehensive order form file 'orderform.txt' has been created in the working directory.\nThe current order has been erased.");
            this.order = null;
        }
    }
    
    public void mouseClicked(MouseEvent event){
        clientsInput.setText("");     
    }
    
    public void mouseEntered(MouseEvent event){}

    public void mouseExited(MouseEvent event){}

    public void mousePressed(MouseEvent event){}

    public void mouseReleased(MouseEvent event){}  
    
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

    public static void createRequestOutput(ArrayList<Food[]> foodList) {
        PrintWriter outputWrite = new PrintWriter(outputFile);
        outputWrite.println("Hamper Order Form\n");
        outputWrite.println("Name: \n");
        outputWrite.println("Date: \n");
        outputWrite.println("Original Request\n");
        for(int i = 0; foodList !=null; i++) {
            order.getHampers().get(i).getClients();
            outputWrite.printf("Hamper:", foodList);
        }

        outputWrite.close();
        

        //order.getHampers();
    
        
    }


    public Order getOrder() {return this.order;}
    
    public static void main(String[] args) { 
        EventQueue.invokeLater(() -> {
            new RequestIO().setVisible(true);        
        });
        createRequestOutput(orderForm);
    }
        
}