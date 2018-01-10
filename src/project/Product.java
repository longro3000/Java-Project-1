//Product class - storing all data of a product.
package project;

public class Product {
		private String Name;
		private Double Price;
		
		//Constructor.
		public Product(String name, double price)
		{
			this.Name=name;
			this.Price=price;
		}
		
		//Getter methods.
		
		public String getName() {
			return Name;
		}
		public Double getPrice() {
			return Price;
		}
		
		//To string method.
		@Override
		public String toString() {
			return Name+" \t"+Price+" €";
		}
}
