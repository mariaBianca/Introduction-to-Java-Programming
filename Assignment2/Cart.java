package Assignment2;
/**
 * @author CONRAD-UWAILA EKHATOR 790213-5396(has talked to Musard regarding another time for presentation due to personal issues)
 * @author MARIA-BIANCA CINDROI 920213-0028
 */


import static  Assignment2.SimpleIO.*;

/**
 * Class representing a shopping cart for the
 * second assignment in DIT948, 2015 edition.
 */

public class Cart  {

	// Array of products (max 100 products)
	private final Product[] products = new Product[100];

	//Position of the first free cell to add a product
	private int position = 0;

	/**
	 * Return the list of products
	 * @return
	 */
	public Product[] getProducts(){
		return products;
	}

	/**
	 * Add a product to the list
	 * @param product
	 */

	public void addProduct(Product product) {
		products[position] = product;  //Add a product to the list
		position++;  //Move to next position, in order to be able to store the next product, if needed
	}

	/**
	 * Add an array of products to the list
	 * @param products
	 */
	public void addProducts(Product[] products) {

		for (Product currentProduct : products) {
			this.products[position] = currentProduct; //Add the current product
			position++; //Move to next position in the list.
		}
	}

	/**
	 * Adds a product several times
	 * @param product
	 * @param howManyTimes number of times to add product 
	 */
	public void addProduct(Product product, int howManyTimes) {		
		for (int i = 0; i < howManyTimes; i++) {
			products[position] = product;
			position++; //add the product until howManyTimes is reached
		}
	}

	/**
	 * Calculate the total price
	 * @return
	 */
	public double totalPrice(){ 
		double total = 0;		

		for (int i = 0; i < position; i++) {
			//take the price of the element on the current position in the list and add it to the total
			total = total + products[i].getPrice(this);
		}
		//return the total sum when the end of the list is reached
		return total;
	}

	/**
	 * String representation of products in a shopping 
	 * cart
	 * Example: 
	 * CD of Leonard Cohen 22.50 SEK. Sold by Javier
	 * TV [discounted by 20.00%]    4000.00 SEK. Sold by Maria
	 */

	public String toString() {
		String result = "";

		for(int i = 0; i < position; i++) {
			Product product = this.products[i];
			result = result + product.toString() + " " + product.getPrice(this) + 
					" SEK, sold by " + product.getSeller() + "\n";
		}
		//Store the elements in a String-type paragraph. The 
		//display jumps to the next line once the position in the list changes.
		//Thus, each product is displayed on a single line followed by its price and the retailer
		//When the all the list is covered, returns the string.
		return result;
	}

}
