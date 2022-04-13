package edu.ucalgary.ensf409;
/*
Copyright Ann Barcomb and Emily Marasco, 2021
Licensed under GPL v3
See LICENSE.txt for more information.
*/


import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.FlowLayout;

public class RequestIO extends JFrame implements ActionListener, MouseListener{

    private Order order;
    private String clients;
    private ArrayList<Food> orderForm;

    private JLabel instructions;
    private JLabel clientsLabel;
    private JTextArea clientsInput;
    private JButton processOrder;
    private JButton addHamper;
    
    public RequestIO(){
        super("Create a Hamper");
        setupGUI();
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
    }
    
    public void setupGUI(){
        
        instructions = new JLabel("Please enter the list of clients that are part of a single hamper.");
        clientsLabel = new JLabel("List of clients:");
        
        clientsInput = new JTextArea("e.g.\nAdult Female\nAdult Male\nChild Under 8", 5, 15); 
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
    
    public void actionPerformed(ActionEvent event){

        clients = clientsInput.getText();
        String printMessage = "";

        if(event.getSource().equals(addHamper)){
            if (validateClientInput()){
                if (this.order == null){
                    createOrderFromInput();
                    //create client objects, then create hamper using clients, then addHamperToOrder()
                }
                else{
                    //create client objects, then create hamper using clients, then addHamperToOrder()

                }
                JOptionPane.showMessageDialog(this, "New Hamper is:\n" + clients);
            }
        }
        else if (event.getSource().equals(processOrder)){
            JOptionPane.showMessageDialog(this, "Hampers To Be Processed:\n" + printMessage);
      /*      try{
                orderForm = Inventory.findHamperCombo(order.calculateNutrition());
                Inventory.completeOrderForm(orderForm);
                createRequestOutput(orderForm);
            } catch (InsufficientInventoryException e){
                JOptionPane.showMessageDialog(this, "Order could not be processed due to insufficient inventory.");
            }
      */      this.order = null;
        }
    }
    
    public void mouseClicked(MouseEvent event){
        clientsInput.setText("");     
    }
    
    public void mouseEntered(MouseEvent event){
        
    }

    public void mouseExited(MouseEvent event){
        
    }

    public void mousePressed(MouseEvent event){
        
    }

    public void mouseReleased(MouseEvent event){
        
    }  
    
    private boolean validateClientInput(){
        boolean inputValid = true;
        
        StringTokenizer tokenizer = new StringTokenizer(clients, "\n");
        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if (!token.equals(ClientTypes.ADULTFEMALE.toString()) && 
            !token.equals(ClientTypes.ADULTMALE.toString()) && 
            !token.equals(ClientTypes.CHILDOVER8.toString()) &&
            !token.equals(ClientTypes.CHILDUNDER8.toString())){
                inputValid = false;
                JOptionPane.showMessageDialog(this, token + " is an invalid client type.");
                JOptionPane.showMessageDialog(this, "The valid client types are 'Adult Female', 'Adult Male', 'Child Over 8', and 'Child Under 8'." +
                "\nRefer to this example for the required input format:" + "\nAdult Female\nAdult Male\nChild Under 8");
            }
        }
        
        return inputValid;
    }

    public void createOrderFromInput(){

    }

    public void createRequestOutput(){

    }

    public Order getOrder() {return this.order;}


    
    public static void main(String[] args) 
    {
        
        EventQueue.invokeLater(() -> {
            new RequestIO().setVisible(true);        
        });

        Database database = new Database("jdbc:mysql://localhost/food_inventory","student","ensf");
    }
        
}