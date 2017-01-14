package Assignment1;

/**
 * course dit948 - Programming
 * Assignment 1
 * @author Maria-Bianca Cindroi 
 * This program takes an arithmetic operations, transforms it into a postfix operations and then evaluates it.
 * */
import static Assignment1.SimpleIO.*;


public class Assignment1 {

	/**This is the priority subroutine
	 * Prioritises the operands according to their precedence.
	 * */
	//Assigns priority according to the type of operand. Eg1. '*'/'/' has a higher priority than '-'/'+'
	public static byte p(char priority) {
		byte p = -1; //initial priority is -1

		if (priority == '+' || priority == '-') {
			p = 0; //priority of + and - is 0
		} else if (priority == '*' || priority == '/') {
			p = 1; //priority of / and * is 1
		}	
		return p;
		/**@param p: returns the priority of the operator */
	}

	/**This is the method used for checking input. This calculator cannot start with an operator.
	 * Checks if the equation given has a valid or invalid format and returns an appropriate message.
	 * */
	public static int CheckInput(String input) {

		/**@param flag: flags an invalid operation
		 * @param firstChar: holds the first character of the equation
		 * @param lastChar: holds the last character of the equation
		 * @param leftPar: holds the number of left parentheses
		 * @param rightPar: holds the number of right parentheses
		 * @param operandCounter: holds the number of operators
		 * @param numberCounter: holds the number of numbers
		 * */
		//Authors' rule: the calculator cannot and will not start with an operator. Thus any equation of form '+a...','-a...','*a...','/a...' is invalid
		int flag = 0;
		char firstChar = input.charAt(0);
		int length = input.length(); // length of the string
		char lastChar = input.charAt(length - 1); 
		int leftPar = 0;
		int rightPar = 0;
		int operandCounter = 0;
		int numberCounter = 0;
		String input2 = input + "."; // helping string. When there is a need to check the next character in the case of lastChar for the initial string
		//the next character cannot be checked as it is non-existent

		/**Checks the validity of the beginning of the equation field. */
		//The equation cannot start with ')', operator or letters
		if (firstChar == '(' || firstChar == '0' || firstChar == '1' || firstChar == '2'
				|| firstChar == '3' || firstChar == '4' || firstChar == '5'
				|| firstChar == '6' || firstChar == '7' || firstChar == '8'
				|| firstChar == '9'){			/**Checks the validity of the end of the equation field- */
			//The equation cannot end in '(', operator or letters
			if (lastChar == ')' || lastChar == '0' || lastChar == '1'
					|| lastChar == '2' || lastChar == '3' || lastChar == '4'
					|| lastChar == '5' || lastChar == '6' || lastChar == '7'
					|| lastChar == '8' || lastChar == '9') {
				;
				/**Checks the content of the equation field.*/
				//A valid equations cannot cannot have more or less '(' than ')'. Eg2. '((2+3)-5' is mathematically incorrect 
				// Count the number of parentheses
				for (int i = 0; i < length; i++) {
					if (input.charAt(i) == '(')
						leftPar++;
					if (input.charAt(i) == ')')
						rightPar++;
					//A valid equation cannot have more operators than operands. Eg3. '2+3+' is mathematically incorrect 
					// Count the number of operators
					if (input.charAt(i) == '-' || input.charAt(i) == '+'
							|| input.charAt(i) == '/' || input.charAt(i) == '*')
						operandCounter++;
					// Check the number of digits. We need such a check in order to compare the number of operators with the numbers of digits.
					//The number of operators cannot be bigger than the number of operands. Taking Eg3. '(2+3+)', numberCounter==2; operandCounter==2, 
					//but the equation is invalid, thus, the general rule: numberCounter == operandCounter + 1
					if (input.charAt(i) == '0' || input.charAt(i) == '1'
							|| input.charAt(i) == '2' || input.charAt(i) == '3'
							|| input.charAt(i) == '4' || input.charAt(i) == '5'
							|| input.charAt(i) == '6' || input.charAt(i) == '7'
							|| input.charAt(i) == '8' || input.charAt(i) == '9')
						numberCounter++;
				}
				// Check if the right and left parentheses are same number. If
				// not the equation is invalid. Eg4. 4+((4+4) with rightPar == 1 whereas leftPar == 2 and the equation is invalid
				if (leftPar == rightPar) {
					// Ignore the sign of the first number if exists
					if (firstChar == '-' || firstChar == '+')
						operandCounter--;
					//The general rule above 'numberCounter == operandCounter + 1' is properly functional only when the operand is one digit thus,
					//there is the necessity of a check if the operator is a one digit or not. Eg5. '(23+3+)' operandCounter==2; numberCounter==3, 
					//but the equation is invalid because every digit has been counted, not every operator.
					// Check if digit or number. If number then decrease the numberCounter with how many positions the operand occupies
					//E.g. operator == 123, initial numberCounter == 3
					for (int i = 0; i < length; i++) {
						if (input2.charAt(i) == '0' || input2.charAt(i) == '1'
								|| input2.charAt(i) == '2'  || input2.charAt(i) == '3'
								|| input2.charAt(i) == '4'	|| input2.charAt(i) == '5'
								|| input2.charAt(i) == '6'  || input2.charAt(i) == '7'
								|| input2.charAt(i) == '8'	|| input2.charAt(i) == '9') {
							if (input2.charAt(i + 1) == '0' || input2.charAt(i + 1) == '1'
									|| input2.charAt(i + 1) == '2' || input2.charAt(i + 1) == '3'
									|| input2.charAt(i + 1) == '4' || input2.charAt(i + 1) == '5'
									|| input2.charAt(i + 1) == '6' || input2.charAt(i + 1) == '7'
									|| input2.charAt(i + 1) == '8' || input2.charAt(i + 1) == '9')
								numberCounter--;//Eg5. operator=123, numberCounter==1 as the next 2 positions are also digits
						}
					}
					//If the operator inside of a parentheses has a sign, Eg6. (-2+3)+5 operandCounter == 3 and numberCounter == 3 but the equation
					//is valid. Thus if an operator comes after '(' then ignore it
					// Ignore the sign of the number inside the equation
					for (int i = 0; i < length; i++) {
						if (input2.charAt(i) == '(') {
							if (input2.charAt(i + 1) == '-'
									|| input2.charAt(i + 1) == '+')
								operandCounter--;
						}
					}
					if (operandCounter == numberCounter - 1) {
						// Check the position of the operand against
						// parentheses. Wrong: (4-5+)4
						for (int i = 0; i < length; i++) {
							if (input2.charAt(i) == '+'
									|| input2.charAt(i) == '-') {
								if (input2.charAt(i + 1) == ')')
									flag = 1;
							}
						}
					}//when the flag == 1, then the operations is invalid
					else
						flag = 1;
				} else
					flag = 1;
			} else
				flag = 1;
		} else
			flag = 1;

		return flag;
		/**@param flag: returns the validity of the equation: 0 for valid; 1 for invalid.
		 */

	}
	/**This is a method used to reverse a string made out of numbers.
	 * This method is used in the evaluation of the postfix equation method.
	 */
	public static String reverse(String string){ // method to reverse a string
		String reverse = "";
		String stack = "";

		/**This field selects the numbers and keeps them into their format.
		 */
		//35 will not become 53
		for (int i = 0; i < string.length(); i++){
			if (string.charAt(i) != ' '){
				stack = stack + string.charAt(i);
			}
			else if (string.charAt(i) == ' '){
				reverse = stack + " "+ reverse;
				stack = "";
			}
		}
		return reverse;
		/**@return reverse: returns the reverse of the input string
		 */}


	/** This is a method that evaluates a postfix equation. 
	 */
	public static int evaluate (String output){
		int finalResult = 0; 
		output = output+"  "; // add empty spaces as we might need j+1 position
		String valueGatherer = ""; //gathers the values only in the given string
		//in order to get all the values calculated, the method must go through
		//the equation as many times as the number of operands existent
		int operandCounter = 0; //counts the operators
		int result = 0;
		String remainder = "";
		/**@param remainder: holds the not used part of the string
		 * @param result: holds the temporary result between 2 numbers in the string
		 * @param finalResult: holds the final result returned to main
		 */		

		//Check the number of operators as they represent the number of operations are made
		for (int i = 0; i < output.length(); i++){
			if (output.charAt(i) == '-' || output.charAt(i) == '+'|| output.charAt(i) == '/' || output.charAt(i) == '*'){
				operandCounter++;
			}
		}
		/**Go through the postfix equation as many times as many operands exist in it.*/
		for (int i = 0; i < operandCounter; i++){
			String stack = ""; //stack for the calculation
			String stack2 = ""; //temporary helping stack 
			//println("updated output:"+output);

			for (int j= 0; j < output.length(); j++){
				/**If the element is an operand, hold it.*/ 
				if (output.charAt(j) != ' ' && output.charAt(j) != '+' && output.charAt(j) != '-' && output.charAt(j) != '*'
						&& output.charAt(j) != '/'){
					valueGatherer = valueGatherer + output.charAt(j);
				}
				/**If the element is a space, check the next element.*/
				else if (output.charAt(j) == ' '){
					stack = valueGatherer + " " +stack;	//println("taken stack is:"+stack);
					//it is critical to remove empty spaces found in front of the stack for proper calculation
					if (stack.charAt(0) == ' '){
						stack = stack.substring(1);
					}
					valueGatherer = ""; //empty the valueGatherer to be able to hold the next operand
				}
				/**If the element is an operator, start the calculation algorithm.*/
				else if (output.charAt(j) == '+' || output.charAt(j) == '-' || output.charAt(j) == '*' 
						|| output.charAt(j) == '/'){
					//remainder = output.substring(j);//println("output for remainder:"+output);//println("j:"+j);//println("j+1:"+(j+1));//println("remainder:"+remainder);
					int flag = 0; // flags if one operand of an equation is already stored
					//stack = stack + " ";//println(stack);
					String number1 = ""; //operand1 
					String number2 = ""; //operand2
					for (int k = 0; k < stack.length(); k++){
						/**Store the operands that will be used in the equation.*/
						//if no operand is stored, flag = 0
						if (flag == 0){
							//println("char:"+stack.charAt(k));
							if (stack.charAt(k) != ' '){
								//start with operand2 as the stack is reversed
								number2 = number2 + stack.charAt(k); 
							}
							else if (stack.charAt(k) == ' '){
								flag = 1;
								//println("no2:"+number2);
							}
						}
						else if (flag == 1){
							if (stack.charAt(k) != ' '){
								number1 = number1 + stack.charAt(k);
							}
							else if (stack.charAt(k) == ' '){
								/**The operands are removed from the stack.*/
								stack = stack.substring(k);
								//println("stack:"+stack);//println("no1:"+number1);//println("no2:"+number2);
								/**Find the operator that will be used in the equation.*/
								//the remainder becomes the string starting with next space after the operator
								if (output.charAt(j) == '+'){
									result = Integer.parseInt(number1) + Integer.parseInt(number2);
									remainder = output.substring(j+1);
								}
								if (output.charAt(j) == '-'){
									result = Integer.parseInt(number1) - Integer.parseInt(number2);
									remainder = output.substring(j+1);
								}
								if (output.charAt(j) == '*'){
									result = Integer.parseInt(number1) * Integer.parseInt(number2);
									remainder = output.substring(j+1);
								}
								if (output.charAt(j) == '/'){
									result = Integer.parseInt(number1) / Integer.parseInt(number2);
									remainder = output.substring(j+1);
								}
								//println("result:"+result);
								/**The stack's first value becomes the result between the selected operands.*/
								stack = result + stack;
								//remove empty value found at the beginning of the stack for proper computation further on
								if (stack.charAt(0) == ' '){
									stack = stack.substring(1);
								}
								//println("stack is:"+stack);//println("the equation is:"+number1+output.charAt(j)+number2);//print(stack);//println("first stack:"+stack);
								//reverse the stack back to the normal "output" shape
								stack2 = reverse(stack);
								//println("my stack"+stack);//println("my remainder"+remainder);
								//remove empty spaces from the beginning of the stack for proper computation
								if (remainder.charAt(0) == ' '){
									remainder = remainder.substring(1);
								}
								//stack2 = stack2.substring(1);	//println("stack2:"+stack2);//println("remainder"+remainder);
								/**The new output is updated.*/
								//the output becomes the new stack coupled with the reminder
								output = stack2 + remainder;
								//println("output:"+output);//println("final stack:"+stack);//println("remainder:"+remainder);//println("");
								//the final result takes its value
								finalResult = result;
								//println("final result:"+finalResult);
								//the computation result is emptied, same with the operands
								//to allow a new computation to take place
								result = 0;
								number1 = "";
								number2 = "";
								/**In order to update the stack and its position, break (legally) from the loop.*/
								// in order to be able to update the new stack and its position, 
								//we have to find a legal way to break out of the for-loop
								//thus the position goes out of its boundaries(k < length of stack) 
								k = stack.length() + 1;
							}
						}
					}
					/**In order to update the output and its position, break the loop*/
					break; //in order to update the new output and its position, break
				}
			}
		}

		return finalResult;
		/**@return finalResult: returns the evaluation of the postfix equation*/
	}

	/** Transform the equation to RPN: Task 1 
	 * This method transform an infix equation to a postfix one.
	 */

	public static String RPN(String input) {

		int m = input.length(); //length of the input
		String stack = "";
		String input2 = input + "."; //helping string.
		String output = ""; 
		/**@param stack: temporary stack used to hold parentheses and operators in order to sort efficiently
		 * @param output: final result
		 */

		for (int i = 0; i < m; i++){
			/**Checks if it is a one-digit or a multiple-digit number and outputs the result.*/
			//if the checked position is a digit, then output it
			if (Character.isDigit(input.charAt(i))){
				output = output + input.charAt(i);
				for (int j = i + 1; j < m + 1; j++ )
				{
					//if the checked position is a digit, then output it. Operand is not a digit, but a number
					if (Character.isDigit(input2.charAt(j))){
						output = output + input2.charAt(j);
						i = j;
					}else break;
				}
				/**This algorithm prioritises the operators met and stacks it according to their precedence
				 * or position in the equation.
				 * Outputs when a higher priority or a closed parentheses is found until the stack empties*/
				//print a space on the output
				output = output + " ";
			}else if(input.charAt(i) == '(') {
				//if the position is a '(' then save it to stack
				stack = input.charAt(i) + stack;	
			}
			else if(input.charAt(i) == ')'){
				//if the position is a ')' then, as long as the stack first position is not ')', output the first position of the stack
				while(stack.charAt(0) != '('){
					output += stack.charAt(0) + " ";
					stack = stack.substring(1); //remove the stack's first position and its content
				}
				stack = stack.substring(1); //remove the stack's first position and its content
			}
			else if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '/'
					|| input.charAt(i) == '*'){
				//if the position in the input is an operator 
				//check if the stack is empty, or the priority of the stack's first element is smaller than that of the input position
				//or if the stack's first element == '(', then output the stack's first element
				while(!(stack.isEmpty() || p(stack.charAt(0)) < p(input.charAt(i)) || stack.charAt(0) == '('))						
				{
					output += stack.charAt(0) + " ";
					if(stack != "")
					{
						stack = stack.substring(1);//remove the stack's first position and its content
					}
				}
				stack = input.charAt(i) + stack; //if operator, make the position in input the first position of the stack
			}
		}
		while(!stack.isEmpty())
		{//as long as the stack is not empty, output the stack's first element and then delete from the stack
			output += stack.charAt(0) + " ";
			stack = stack.substring(1); 
		}
		//println(output);
		//println(stack);
		return output; 
		/**@param output: returns the postfix equation to the main method*/
	}



	public static void main(String[] args) {

		/**@param firstInput: used to check the desired usability of the calculator
		 * @param input: used to implement the desired equation
		 */
		String firstInput; 
		String input = " "; 

		/** The User Interface: Main Task */
		/** GUI field */
		println("+****************************************+");
		println("*                                        *");
		println("*      Welcome to DIT948 Calculator      *");
		println("*                                        *");
		println("+****************************************+");
		println("");
		print("Press \"E\" to exit or any other button to continue>");
		firstInput = readString(); //read if to close or not the calculator
		int flagInput = 0; //flagInput == 0, continue calculating, flagInput == 1, shut the program
		// Check if to exit or not
		if (firstInput.equals("E") || firstInput.equals("e")) {
			System.exit(0);
		}
		int check = CheckInput(input);
		do {//check equations as long as the shut down is not selected
			print("Please enter an arithmetic expression to evaluate>");
			do {
				input = readString(); //read the equation
				check = CheckInput(input);//check if the equation is valid
				if (check == 1) {
					//if the equation is invalid, try again
					println("");
					println("The arithmetic expression you have entered is invalid.");
					println("Please enter an arithmetic expression to evaluate>");
				}
			} while (check != 0);
			println("");

			String output = RPN(input); //transform the equation to the Postfix
			print("The RPN representation of your expression is> " + output);
			println("");
			println("");
			int result = evaluate(output); //evaluates the Postfix
			print("The final result is>" + result);
			println("");
			println("");
			print("Press \"E\" to exit or any other button to continue>");
			firstInput = readString(); //choose if to continue or to close the calculator
			println("");
			if (firstInput.equals("E") || firstInput.equals("e")) {
				println("Bye Bye");
				System.exit(0);
				flagInput = 1;
			}
		} while (flagInput != 1);
	}
}


