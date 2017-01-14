package theRobot;

import static theRobot.Random.*;

/**Builds the black(bad) robot.
 * Implements the Runnable interface. It is meant to be executed by any tread.
 * Initialises the good(red) robot as the move method is needed for it as well, in order to be
 * able to stop the program when the user enters the walls of the city.
 *
 *@author Maria-Bianca Cindroi
 *@author Conrad Uwaila Ekhator
 * */

import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Robot;
import becker.robots.RobotException;

public class BadRobot extends Robot implements Runnable {

	//initialise the robot
	Optimus  theRobot;
	boolean breakme;
	
	// Creating constructor for the Bad Robot with its specific city, direction, position and type
	public BadRobot(City arg0, int arg1, int arg2, Direction arg3, Optimus theRobot) {
		super(arg0, arg1, arg2, arg3);
		this.setSpeed(2);
		this.theRobot = theRobot;

	}

	//To turn right, turn left three times
	public void turnRight() {
		this.turnLeft();
		this.turnLeft();
		this.turnLeft();
	}

	//move towards random directions
	public void move() {

		int random = randomInt(4);

		/* Telling what to do for North, South, East, and West. */
		switch (random) { 
		case 0: {
			if (frontIsClear())
				super.move();
			break;
		}
		case 1: {
			this.turnLeft();
			this.turnLeft();
			if (frontIsClear())
				super.move();
			break;
		}
		case 2: {
			this.turnRight();
			if (frontIsClear())
				super.move();
			break;
		}
		case 3: {
			this.turnLeft();
			if (frontIsClear())
				super.move();
			break;
		}
		}
		explode();

	}

	/** If the good robot finds itself in the same position with the bad robot, then explode!
	 * and break the user robot.
	 */

	public void explode() {
		if (this.getIntersection() == theRobot.getIntersection()) {
			//System.out.println("Sorry. You died...");
			theRobot.breakRobot("Sorry. You died...");
		}
	}

	@Override
	public void run() {
		while (true)
			this.move();

	}
	
	protected void breakRobot() {

		try {

			super.breakRobot("");

		} catch (RobotException robot) {

	
	
		}
		}


}
