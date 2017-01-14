package Assignment2;

/**
 * @author CONRAD-UWAILA EKHATOR 790213-5396(has talked to Musard regarding another time for presentation due to personal issues)
 * @author MARIA-BIANCA CINDROI 920213-0028
 */

import static Assignment2.SimpleIO.*;

/**
 * Class representing a shopping cart application for the
 * second assignment in DIT948, 2015 edition.
 * This is the main class for the application, interacting
 * with the customer, adding to the cart different products to
 * be purchased and finally calculating the total amount to be payed
 */

public class Main {

	/**
	 *  Allows a shopkeeper to enter the details for a product
	 *  to be purchased by a customer 
	 *  @param cart the shopping cart of a given customer
	 */
	public static void askCustomer(Cart cart){
		// Code to read from console the product name, seller,
		// price, productAmount of products, discount and
		// if Buy2Take3 applies.

		// Then create a product of the correct type
		// and add it to the shopping cart

		String retailer = "";  //Retains the person selling's name
		String product = ""; //Retains the name of the product
		Double price; // Retains the price of the product
		int productAmount = 0; // Retains the amount of products
		double discount = 0; // Retains the discount percentage
		String buyGet; // Character that checks if buy2get3 discount applies

		print("Product name: >");
		product = readString();
		//println("");
		print("Seller: ");
		retailer = readString();
		//println("");
		print("Price: ");
		price = readDouble();
		//println("");
		print("How many: ");
		productAmount = readInt();
		//println("");
		print("Discount (enter 0 if no discount applies): ");
		discount = readDouble();
		//println("");
		print("Does buy 2 get 3 apply? Y/N");
		buyGet = readString();
		//Create a new product
		Product p = new Product(retailer, product,price); 
		//If the product fits for discount, update the product's needed fields
		if (discount > 0) {
			p = new DiscountedProduct(p,discount);
		}
		//If the buy2Get3 offer applies, update the product's needed fields
		//The reason a string is needed and the first character of it is checked, see discussion for string "choice" below
		if (buyGet.charAt(0) == 'Y' || buyGet.charAt(0) == 'y') {
			p = new Buy2Take3Product(p);
		}
		//Add product to the cart
		while (productAmount > 0) {
			cart.addProduct(p);
			//With every added product, deduct from the amount until no more products are left to be added
			productAmount--;
		}	
	}


	// Main method to interact with a customer 
	public static void main(String[] args) {
		String choice = " ";
		println("Welcome to DIT958 shop!");
		println("What's your name?");
		String customer = readLine();
		println("Hi, "+ customer +". Please choose one of the following options:");
		println("");

		//Create a new cart
		Cart cart = new Cart();

		//Implement the user interface here
		// Ask the user to choose 0 (buy product) or
		// 1 (checkout), depending on what they want to do.
		// Use the method askCustomer
		// See TestCases.txt for several examples

		println("Enter 1 to buy a product.");
		println("Enter 0 to checkout and proceed with the payment.");
		//Considering the situation in which the customer mistakenly presses 2 keys at a time and the distance on the keyboard
		//between 1 and 0, we decided to take only the first character of the keyboard input to check if the choice
		//is valid or not.

		//As long as not checkout is chosen, proceed.
		while (choice.charAt(0) != '0'){
			// Check the choice of the customer.
			choice = readString();
			//Check if the choice is valid.
			if (choice.charAt(0) != '0' && choice.charAt(0) != '1') {
				println("Your choice is invalid. Please try again!");
			}

			else if (choice.charAt(0) == '1'){
				askCustomer(cart);
				println("Enter 1 to buy a product.");
				println("Enter 0 to checkout and proceed with the payment.");
				//choice = readString();
			}
		} 


		println("");
		//Display all the products in the cart, one by one
		println(cart);
		println("In total, you have to pay " +cart.totalPrice() + " SEK.");

	}
}