package theRobot;

/** This class builds the city. 
 * @author Maria-Bianca Cindroi


import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Wall;

public class GameWorld extends City {

	static int size;

	public GameWorld() { // Builds the city
		super(size, size);

		for (int i = 0; i < size; i++) {
			Wall north = new Wall(this, 0, i, Direction.NORTH);
			Wall west = new Wall(this, i, 0, Direction.WEST);
			Wall south = new Wall(this, size - 1, i, Direction.SOUTH);
			Wall east = new Wall(this, i, size - 1, Direction.EAST);
		}
	}
}
