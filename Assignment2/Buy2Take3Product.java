package Assignment2;
/**
 * @author CONRAD-UWAILA EKHATOR 790213-5396(has talked to Musard regarding another time for presentation due to personal issues)
 * @author MARIA-BIANCA CINDROI 920213-0028
 */

import static Assignment2.SimpleIO.*;

import com.sun.prism.paint.Color;
/**
 *  Subclass representing a  product to be  purchased 
 *  (using the formula "buy 2 take 3") for the second 
 *  assignment in DIT948, 2015 edition.
 *  It extends the <tt>Product<tt> class with one instance 
 *  variable
 */

public class Buy2Take3Product extends Product {

	//original product
	private final Product original;

	/**
	 * Construct a Buy2Take3 product
	 * If the price of this product can not be reduced
	 * you should print a message to the user and terminate the 
	 * program
	 * @param original
	 */
	public Buy2Take3Product(Product original) {
		// if the price can not be reduced you should print a message and
		// terminate the program. Use "System.exit(0);" to terminate. 	

		super(original);
		this.original = original;

		if (canBeReduced()) {
			;
		}
		else {
			println("We are sorry to announce but, Buy2Take3 DOES NOT APPLY for this product. Have a nice day! ");
			System.exit(0);
		}
	}

	/**
	 * Return false if the product price can not be
	 * reduced
	 * @return 
	 */
	public boolean canBeReduced() {
		// You can not discount the price of Buy2Take3 product
		boolean check = true;

		//Check if the original product can be considered an instance of the discounted.
		if (this.original instanceof DiscountedProduct) {
			//If not, return false
			check = false;
		}

		return check;
	}

	/**
	 * Return the unit price of a product using the
	 * formula "Buy2Take3"
	 * @param cart shopping cart
	 * @return unit price 
	 */
	public double getPrice(Cart cart) {
		// calculate unit price of this product purchased
		// as Buy2Take3
		Product[] chosen = cart.getProducts();
		int counter = 0;

		for(int i = 0; i < chosen.length; i++) {
			if (chosen[i] == this) {
				counter++;
				//count how many times the specific product appears in the cart
			}
		}
		//find out how many times the Buy2Take3 applies for the specific product.
		int howManyTimes = counter / 3;
		
		return original.getPrice(cart) * (counter - howManyTimes) / counter;
		//retunr the updated price!
	}
}