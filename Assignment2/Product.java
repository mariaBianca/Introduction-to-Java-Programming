package Assignment2;
/**
 * @author MARIA-BIANCA CINDROI
 */

import static Assignment2.SimpleIO.*;

/**
 *  Class representing a product to be purchased for the
 *  second assignment in DIT948, 2015 edition.
 */
public class Product {
	// Name of the seller
	private final String seller;

	// Name of the product
	private final String name;

	// Price of the product
	private final double price;

	/**
	 * Construct a new Product given the following parameters
	 * @param seller
	 * @param name
	 * @param price
	 */	
	public Product(String seller, String name, double price) {
		this.seller = seller;
		this.name   = name;
		this.price  = price;
	}

	/**
	 * Construct a new Product from a given product
	 * @param original
	 */	
	// "original" parameter: original product.

	public Product(Product original) {
		// Use the constructor implemented above
		this(original.getSeller(), original.getName(), original.getPrice(null));		
	}

	/**
	 * Return the seller of  this product
	 * @return seller
	 */	
	//I shall return it! 
	public final String getSeller() {
		//code here: 
		//Try and get seller! It's trying to escape! Ruuuun!!!
		return this.seller; //Thank God I got it!!!!!
	}

	/**
	 * Return the name of  this product
	 * @return name
	 */
	public final String getName() {
		//code here
		//Only used the "this", cos the "that" doesn't work. (Don't ask me why!)
		return this.name;
	}

	/**
	 * Return the price of  this product
	 * @param cart
	 * @return price
	 */	
	public double getPrice(Cart cart) {
		//code here: "not much code to do here"
		return this.price;
	}

	/**
	 * Returns true if the price of this product
	 * can be reduced
	 * @return 
	 */	
	public boolean canBeReduced() {
		//Nothing to do here:
		//"didn't do anything here!"
		return true;
	}

	/**
	 * Return the name of the product
	 */
	public String toString() {
		// code here: "not much code to do here either"
		return name;

	}
}
