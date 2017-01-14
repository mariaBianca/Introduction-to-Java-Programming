package theRobot;

/**
 * Takes the prize ad puts it in the city.
 * @author Maria-Bianca Cindroi
 * */
import java.awt.Graphics;

import javax.swing.ImageIcon;


import becker.robots.icons.Icon;
import static theRobot.Random.*;

public class PaintPrize extends Icon {

	//construction
	public PaintPrize(Graphics g) {
		super();
		this.paintIcon(g);
	}

	//paint the icon
	public void paintIcon(Graphics g) {
		ImageIcon gift = new ImageIcon("src/images/Gift.png");
		g.drawImage(gift.getImage(), randomInt(GameWorld.size), randomInt(GameWorld.size), null);

	}
}
