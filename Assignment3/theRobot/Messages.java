package theRobot;

import javax.swing.JOptionPane;

/**
 * Displays the dialog boxes according to what the user does: wins or looses.
 * @author Maria-Bianca Cindroi
 * @author Conrad Uwaila Ekhator
 * */

public class Messages {

	//loosing message
	public static void loseDialogBox() {
		String message = "You Lose! Do you want to restart?";
		int answer = JOptionPane.showConfirmDialog(null, message, message, JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			Main.restart();
		} else if (answer == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}
	
	//winning message
	public static void winDialogBox() {
		String message = "You Won! Do you want to restart?";
		int answer = JOptionPane.showConfirmDialog(null, message, message, JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			Main.restart();
		} else if (answer == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}
	//confirm exit
	public static void confirmExitDialogBox(){
		int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Please, confirm!", JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			Main.restart();
		} else if (answer == JOptionPane.NO_OPTION) {
			System.exit(0);
		}

	}
}
