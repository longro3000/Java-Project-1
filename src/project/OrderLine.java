//Orderline class - storing all data of an orderline.
package project;

import java.io.Serializable;

public class OrderLine implements Serializable {

		private static final long serialVersionUID = 1L;
		private String name;
		private double price;
		private int quantity;
		
		//Constructor.
		public OrderLine(String name, double price, int Quan)
		{
			this.name = name;
			this.price = price;
			this.quantity = Quan;
		}
		
		//Getter methods.
		public double getPrice(){
			return price;
		}
		public String getName(){
			return name;
		}
		public int getQuan(){
			return quantity;
		}
		
		//method to calculate total price of an orderline. 
		public double totalprice(){
			return quantity*price;
		}
	}

