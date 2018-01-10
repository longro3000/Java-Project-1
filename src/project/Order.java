//Order class - storing information of an order.
package project;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.io.Serializable;
public class Order implements Serializable{ 

	private static final long serialVersionUID = 1L;
	public ArrayList<OrderLine> OrderLines = new ArrayList<>(); // an Array to store all Orderline of an order.
	private  int Number;										//Order number of an order.
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //a variable to change date to a simple form.
	Date date = new Date();										//variable to store current day.
	private String Customer;									//Customer's name of this order.
	public double total=0.0;									//total price of this order.
	
	//constructor.
	public Order(String customer,int number)
	{
		this.Customer = customer;
		this.Number = number;
	}
	
	//method to add new orderline to this order.
	public void OrderAdd(String Name,double Price, int Quantity)
	{
		OrderLines.add(new OrderLine(Name, Price, Quantity));
	}
	
	//Method to calculate total price of order.
	public double TotalPrice()
	{
		int i;
		for (i=0;i < OrderLines.size(); i++)
			total += OrderLines.get(i).totalprice();
		return total;
	}
	//to String method
	public String toString() {
		int i;
		StringBuilder stringbuilders = new StringBuilder();
		stringbuilders.append("Customer Name :"+Customer+"\n Date :"+dateFormat.format(date)+"\n Order No."+Number);
		stringbuilders.append("\n OrderLine No. \tName \tPrice \tQuantity ");
		for (i=0; i< OrderLines.size(); i++)
			stringbuilders.append("\n "+(i+1)+"\t"+OrderLines.get(i).getName()+" \t"+OrderLines.get(i).getPrice()+" \t"+OrderLines.get(i).getQuan()+" \t");
		stringbuilders.append("\n Total Price: "+TotalPrice()+" €");
		return stringbuilders.toString();
	}
	
}
//End of file.
