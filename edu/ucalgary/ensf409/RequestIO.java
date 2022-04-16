package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.FlowLayout;

public class RequestIO extends JFrame implements ActionListener, MouseListener{

    private Order order;
    private String clients;
    private ArrayList<Food[]> orderForm;
    private Database workingDB;

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

    public Client[] createClientList(StringTokenizer tokenizer){
        ArrayList<Client> newClientList = new ArrayList<Client>();
        Client[] newClientArray;
        int i;

        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if (token.equals(ClientTypes.ADULTFEMALE.toString())){
                for (i = 0; i < workingDB.getClientList().size(); i++){
                    if (workingDB.getClientList().get(i).getType().equals(ClientTypes.ADULTFEMALE.toString()));
                        break;
                }
            }
            else if (token.equals(ClientTypes.ADULTMALE.toString())){
                for (i = 0; i < workingDB.getClientList().size(); i++){
                    if (workingDB.getClientList().get(i).getType().equals(ClientTypes.ADULTMALE.toString()));
                        break;
                }
            }
            else if (token.equals(ClientTypes.CHILDOVER8.toString())){
                for (i = 0; i < workingDB.getClientList().size(); i++){
                    if (workingDB.getClientList().get(i).getType().equals(ClientTypes.CHILDOVER8.toString()));
                        break;
                }
            }
            else {
                for (i = 0; i < workingDB.getClientList().size(); i++){
                    if (workingDB.getClientList().get(i).getType().equals(ClientTypes.CHILDUNDER8.toString()));
                        break;
                }
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
        String printMessage = "";

        if(event.getSource().equals(addHamper)){

            StringTokenizer tokenizer = new StringTokenizer(clients, "\n");

            if (validateClientInput(tokenizer)){
                if (this.order == null){
                    order = new Order();
                    order.addToOrder(new Hamper(createClientList(tokenizer)));
                }
                else{
                    order.addToOrder(new Hamper(createClientList(tokenizer)));

                }
                JOptionPane.showMessageDialog(this, "New Hamper is:\n" + clients);
            }
        }
        else if (event.getSource().equals(processOrder)){
            JOptionPane.showMessageDialog(this, "Hampers To Be Processed:\n" + printMessage);
            Inventory.findOrderCombo(workingDB.getFoodList(), order.calculateNutrition());
            JOptionPane.showMessageDialog(this, "Made it here!");
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
    
    private boolean validateClientInput(StringTokenizer tokenizer){
        boolean inputValid = true;

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
    
    public static void main(String[] args) { 
        EventQueue.invokeLater(() -> {
            new RequestIO().setVisible(true);        
        });
    }
        
}