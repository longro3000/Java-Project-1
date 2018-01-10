package project;

import java.util.Scanner;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import java.net.URL;

public class Main extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
		private ArrayList<Order> Orders = new ArrayList<>();	//List of Orders
		private ArrayList<Product> Products = new ArrayList<>();//List of Products
	    private JPanel tab1 = new JPanel(new CardLayout()); 					//First Tab - New Order Tab
	    private JPanel tab2 = new JPanel(); 					//Second Tab - Search Tab
	    private JTextArea data1 = new JTextArea(); 				//Text area to display Order
	    private JTextArea data2 = new JTextArea(); 
	    private JTabbedPane jtp = new JTabbedPane(); 			// Tab pannel
	    private JTextField txtCustomer = new JTextField(15); 	//Field to enter customer's name
	    private JTextField txtQuantity = new JTextField(10); 	//Field to enter product's quantity
	    private JTextField txtSearch = new JTextField(10);		//Field to enter Order Number			
	    int n;													//Number of current Order 
	    private int choice;										//ComboBox choice
	    private JComboBox<String> cmbNames = new JComboBox<>(); //ComboBox for List of Products
	public Main()
	{	
		//Create list of Products
		Products.add(new Product("Heart", 7000.0));
		Products.add(new Product("Kidney", 3000.0));
		Products.add(new Product("Blood", 500.0));
		Products.add(new Product("Liver", 5000.0));
		Products.add(new Product("Fresh Meat", 650.0));
		
		//Create ComboBox for list of Products
		for (int i=0;i<Products.size();i++)
		{
			cmbNames.addItem(Products.get(i).toString());
		}
			
		//Tab 1 content
			//"Create new order" Panel
			JPanel NewOrderPanel = new JPanel();
			//Customer name text field
			NewOrderPanel.add(new JLabel("Customer")); 
			NewOrderPanel.add(txtCustomer);
			//Create button
			JButton CreateBttn = new JButton("Create"); 
			CreateBttn.addActionListener(this);
			NewOrderPanel.add(CreateBttn);
			tab1.add("New Order",NewOrderPanel); //Add above panel to tab 1 as first card.
		
			//"Add new orderline to Orders" Panel.
			JPanel OrderPanel = new JPanel();
			OrderPanel.setLayout(new BoxLayout(OrderPanel, BoxLayout.Y_AXIS));
			OrderPanel.add(new JLabel("Product"));
			OrderPanel.add(cmbNames);
			//get the chosen product from the Combobox.
			cmbNames.addActionListener(new ActionListener() { 
	    	    public void actionPerformed(ActionEvent e) {
	    	    	if (cmbNames.getSelectedIndex() != -1 ) 
	    	    	{
	    	    	choice = cmbNames.getSelectedIndex();}
	    	    }
	    	    });
			//Quantity text Field.
			OrderPanel.add(new JLabel("Quantity")); 
			OrderPanel.add(txtQuantity);
			//Add button.
			JButton AddBttn = new JButton("Add");
			AddBttn.addActionListener(this);
			OrderPanel.add(AddBttn);
			//Save button.
			JButton SaveBttn = new JButton("Save");
		    SaveBttn.addActionListener(this);
			OrderPanel.add(SaveBttn);
			//Display all information of the order on screen.
			OrderPanel.add(data1);
			tab1.add("Add Order",OrderPanel); //Add above panel to tab 1 as second cards.
        jtp.addTab("New Order",tab1);
        
        //Tab 2 content.
        
        //Search button.
        JButton SearchBttn = new JButton("Search");
        SearchBttn.addActionListener(this);
        //number of needed Order.
        tab2.add(new JLabel("Order Number :"));
        tab2.add(txtSearch);
        tab2.add(SearchBttn);
        data2.setText("");
        tab2.add(data2);
        jtp.addTab("Search", tab2);
        
        //JFRAME Contents
        this.add(jtp);      

	}
	
	//Actions happened when clicking Buttons
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String command = evt.getActionCommand();
		if (command=="Create") // Action when Create button clicked.
		{
			Orders.add(new Order(txtCustomer.getText(),n+1));
			// Card playout variable to switch Cards of tab 1.
			CardLayout cl = (CardLayout)(tab1.getLayout()); 
		    cl.next(tab1);
		}
		else if (command=="Add") // Action when Add button clicked
		{
			int Temp=Integer.parseInt(txtQuantity.getText()); //Temporary variable to store quantity as a number
			Orders.get(n).OrderAdd(Products.get(choice).getName(), Products.get(choice).getPrice(), Temp);
			//Display updated orders data.
			data1.setText("");
	    	data1.append(Orders.get(n).toString());
		}
		else if (command=="Search") // Action when Search button clicked
		{
			int Temp=(Integer.parseInt(txtSearch.getText()))-1; //Temporary variable to store search number as a number
			data2.setText("");
			if (Temp < 0 || Temp >= Orders.size()) //if the number entered out of data storage.
			{
				data2.append("NO ORDER FOUND");
			}
			else
			{
				//Display found order.
				data2.append(Orders.get(Temp).toString());
			}
		}
		else if (command=="Save") // Action when Save button clicked
		{
		    n++;
		    writeToFile();
		    // Card playout variable to switch Cards of tab 1.
		    CardLayout cl = (CardLayout)(tab1.getLayout());
		    cl.next(tab1);
		    data1.setText("");
		}
	}
		// Read data from file.
	 public void ReadData()
	 {
		 ClassLoader cl = this.getClass().getClassLoader();
		    URL url = cl.getResource("Orders.txt"); 
		    String File="Orders.txt";
		    try (ObjectInputStream file_in 
			        = new ObjectInputStream(new FileInputStream(File))){
			        Orders = (ArrayList<Order>)file_in.readObject();
			    }
			    catch(Exception e) {
		    		  return;
			    }
		 n=Orders.size();
	 }
	 	//Save order to data.
	 public void writeToFile() {
		 String File="Orders.txt";
		    try (ObjectOutputStream file_out
		         = new ObjectOutputStream(new FileOutputStream(File))){
		        file_out.writeObject(Orders);
		    }
		    catch(Exception e) {
	    		  return;
		    }
		}
	 
	public static void main(String[] args) {
		Main f = new Main();
		f.ReadData();
        f.setSize(1000, 1000);
        f.setVisible(true);
	}
}