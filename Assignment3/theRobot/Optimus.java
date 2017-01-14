package theRobot;

import becker.robots.Direction;
import becker.robots.IPredicate;
import becker.robots.Robot;
import becker.robots.RobotException;

/** Extends Becker's Robot.
 * break the user robot
 * check if on top of prize
 * @author Maria-Bianca Cindroi
 * */

public class Optimus extends Robot {

	BadRobot badRobot;
	Prize gift;

	//constructor for optimus
	public Optimus(GameWorld arg0, int arg1, int arg2, Direction arg3, Prize gift, BadRobot badRobot) {
		super(arg0, arg1, arg2, arg3);
		boolean check = true;
		this.gift = gift;
		this.badRobot= badRobot;
	}


	//method to break the user robot
	//see Becker's documentation
	protected void breakRobot(String msg) {

		try {

			super.breakRobot("ouch");

		} catch (RobotException robot) {

		}
		Messages.loseDialogBox();
	}

	//directs the robot to up
	public void up() {
		if (this.getDirection() == Direction.NORTH) {
			this.move();
		} else {
			while (this.getDirection() != Direction.NORTH) {
				this.turnLeft();
			}
		}
	}

	//directs the robot to down
	public void down() {
		if (this.getDirection() == Direction.SOUTH) {
			this.move();
		} else {
			while (this.getDirection() != Direction.SOUTH) {
				this.turnLeft();
			}
		}
	}

	//directs the robot to right
	public void right() {
		if (this.getDirection() == Direction.EAST) {
			this.move();
		} else {
			while (this.getDirection() != Direction.EAST) {
				this.turnLeft();
			}
		}
	}

	//directs the robot to west
	public void left() {
		if (this.getDirection() == Direction.WEST) {
			this.move();
		} else {
			while (this.getDirection() != Direction.WEST) {
				this.turnLeft();
			}
		}
	}

	//Becker's method "isBesideThing" to determine if the robot is on the same intersection with the prize
	//Becker's method "IPredicate.anyThing" to see test if the prize is a thing or a subclass of a thing
	//and the prize is a thing
	 // see Becker's documentation on IPredicate
	public void pick() {
		if (this.isBesideThing(IPredicate.anyThing)) {
			this.pickThing();
			badRobot.breakRobot();
			Messages.winDialogBox();
		}

	}

}
