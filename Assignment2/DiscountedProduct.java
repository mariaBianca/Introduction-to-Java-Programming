package Assignment2;

/**
 * @author CONRAD-UWAILA EKHATOR 790213-5396(has talked to Musard regarding another time for presentation due to personal issues)
 * @author MARIA-BIANCA CINDROI 920213-0028
 */

import static Assignment2.SimpleIO.*;

/**
 * Subclass representing a discounted product to be
 * purchased for the second assignment in DIT948, 2015 edition.
 * It extends the <tt>Product<tt> class with two instance
 * variables
 */

public class DiscountedProduct extends Product {

	// Original product
	private final Product original;

	// Discount in percentage (%)
	private final double discount;

	/**
	 * Construct a discounted product
	 * @param original
	 * @param discount
	 */
	public DiscountedProduct(Product original, double discount) {
		// if the price can not be reduced you should print a message and
		// terminate the program. Use "System.exit(0);" to terminate. 	

		//take the "original" product and check if the price can be reduced
		super(original); 
		if (!(original.canBeReduced())) {
			System.out.println("Price cannot be reduced!");
			//if the price cannot be reduce then, terminate
			System.exit(0);
		}
		
		//re-take the discount and original for this particular product that WILL be reduced
		this.original = original;
		this.discount = discount;
	}

	/**
	 * Return the price of this discounted product
	 * @param cart shopping cart
	 * @return discounted price 
	 */
	public double getPrice(Cart cart) {
		double discountedPrice;
		
		discountedPrice = original.getPrice(cart)*((100-discount)/100);
		//update the price to the discounted one
		return discountedPrice;
	}

	/**
	 * Return the string representation of the product
	 * Example: CD [discounted 20 %] 
	 */
	public String toString() {
		//returns the details of the discounted products
		return original.getName() + "[discounted " + discount + "%]";
	}
}
