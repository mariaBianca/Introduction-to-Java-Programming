package theRobot;

/**
 *Turns the image prize into the needed "thing"
 * @author Maria-Bianca Cindroi
 * */
import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Intersection;
import becker.robots.Thing;

public class Prize extends Thing {

	//constructor that takes the painted prize, its position and turns it into the needed "thing" 
	public Prize(City arg0, int arg1, int arg2, Direction arg3, boolean arg4, PaintPrize arg5) {
		super(arg0, arg1, arg2, arg3, arg4, arg5);
	}

	//Becker's getIntersection() is protected
	//getter method that returns the intersection
	public Intersection getIntersection() {
		return this.getIntersection();
	}

}
