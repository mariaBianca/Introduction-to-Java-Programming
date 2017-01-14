package theRobot;

/**
 * Builds the frame for the program and puts everything together.
 * @author Maria-Bianca Cindroi
 * */

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import static theRobot.Random.*;

import becker.robots.Direction;
import becker.robots.RobotUIComponents;

public class Main {

	static JFrame frame;
	static JPanel panel;
	static BadRobot badRobot;
	static Optimus prime;
	static Prize surprise;
	static GameWorld grid;
	static Thread trobot;
	static int check = 0; //integer used to define the level
	static JRadioButton easy, medium, hard;

	// Stop,remove and restart any thread or running function on the background.	
	@SuppressWarnings("deprecation")
	public static void restart() {

		RobotUIComponents robot = new RobotUIComponents(grid);
		JButton stop = robot.getStartStopButton();
		stop.doClick();
		stop.doClick();

		frame.remove(panel);
		panel = new JPanel();
		addEverything();
		trobot.stop();
		startTheNewThread();

	}

	//add back the components
	public static void addEverything() {
		GameWorld.size = 15;
		if (check == 1){
			GameWorld.size = 10;
			frame.setTitle("Catch the Prize! - Medium");
		}
		if (check == 2){
			GameWorld.size = 8;
			frame.setTitle("Catch the Prize! - Hard");
		}
		if (check == 0){
			GameWorld.size = 15;
			frame.setTitle("Catch the Prize! - Easy");
		}
		GameWorld.showFrame(false);
		grid = new GameWorld();

		RobotUIComponents robot = new RobotUIComponents(grid);

		//set frame properties
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				String ObjButtons[] = {"Yes","No"};
				int prompt = JOptionPane.showOptionDialog(null,"Are you sure you want to exit the game?",
						"Please, confirm!",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
				if (prompt == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		//Messages.confirmExitDialogBox();
		frame.setResizable(false);

		//set panel layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(robot.getCityView());
		frame.add(panel);

		//add the menu bar and its components
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setBounds(0, 0, 540, 20);

		//add the action menu
		JMenu actions = new JMenu("Actions");
		menuBar.add(actions);

		//add the pause item in the menu and its events
		AbstractAction buttonSpacePressed = new AbstractAction(){
			@Override 
			public void actionPerformed(ActionEvent e){
				robot.getStartStopButton().doClick();
			}
		};
		JMenuItem pause = new JMenuItem("Pause/Resume Game");
		pause.setToolTipText("Start/Pause the game");
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent startstop) {
				robot.getStartStopButton().doClick();
			}
		});
		pause.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.
				getKeyStroke(java.awt.event.KeyEvent.VK_SPACE,0), "Space_pressed");
		pause.getActionMap().put("Space_pressed", buttonSpacePressed);

		actions.add(pause);

		//add the restart item in the actions menu
		JMenuItem restart = new JMenuItem("Restart");
		restart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restart();
			}
		});
		actions.add(restart);

		//add the game settings menu
		JMenu settings = new JMenu("Game Settings");
		menuBar.add(settings);

		//Level easy
		easy = new JRadioButton("Easy");
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent q) {
				check = 0;
				restart();
				badRobot.setSpeed(1);
				easy.setSelected(true);
				frame.setTitle("Catch the Prize! - Easy");
				frame.setSize(768, 902);
			}
		});

		//Level medium
		medium = new JRadioButton("Medium");
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent q) {
				check = 1;
				restart();
				badRobot.setSpeed(3);
				medium.setSelected(true);
				frame.setSize(527, 662);
				frame.setTitle("Catch the Prize! - Medium");
				check = 0;
			}
		});

		//Level hard
		hard = new JRadioButton("Hard");
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent q) {
				check = 2;
				restart();
				badRobot.setSpeed(5);
				hard.setSelected(true);
				frame.setSize(431, 567);
				frame.setTitle("Catch the Prize! - Hard");
				check = 0;
			}
		});

		//group the buttons
		ButtonGroup levelgroup = new ButtonGroup();
		levelgroup.add(easy);
		easy.setSelected(true);
		levelgroup.add(medium);
		levelgroup.add(hard);

		settings.add(easy);
		settings.add(medium);
		settings.add(hard);

		//add the movement buttons
		Dimension buttonsize = new Dimension(100, 100);

		//add the up button and its events
		AbstractAction buttonUpPressed = new AbstractAction(){
			@Override 
			public void actionPerformed(ActionEvent e){
				prime.up();
			}
		};
		JButton up = new JButton("Up");
		up.setMaximumSize(buttonsize);
		up.setAlignmentX(Component.CENTER_ALIGNMENT);
		up.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				prime.up();
			}
		});
		up.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.
				getKeyStroke(java.awt.event.KeyEvent.VK_UP,0), "Up_pressed");
		up.getActionMap().put("Up_pressed", buttonUpPressed);
		panel.add(up);

		//add the pick button to its events
		AbstractAction buttonPickPressed = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				prime.pick();
			}
		};
		JButton pick = new JButton("PICK");
		pick.setMaximumSize(new Dimension(100, 100));
		pick.setAlignmentX(Component.CENTER_ALIGNMENT);
		pick.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.
				getKeyStroke(java.awt.event.KeyEvent.VK_ENTER,0), "Enter_pressed");
		pick.getActionMap().put("Enter_pressed", buttonPickPressed);
		pick.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				prime.pick();
			}

		});
		panel.add(pick);

		//add the left button and its events
		AbstractAction buttonLeftPressed = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e){
				prime.left();
			}
		};
		JButton left = new JButton("Left");
		left.setMaximumSize(buttonsize);
		left.setAlignmentX(Component.CENTER_ALIGNMENT);
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				prime.left();
			}
		});
		left.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
		put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_LEFT,0), "LeftArrow_pressed");
		left.getActionMap().put("LeftArrow_pressed", buttonLeftPressed);

		panel.add(left);

		//add the right button and its events
		AbstractAction buttonRightPressed = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println(e.getActionCommand());
				//System.exit(0);
				prime.right();
			}
		};
		JButton right = new JButton("Right");
		right.setMaximumSize(buttonsize);
		right.addActionListener(buttonRightPressed);
		right.setAlignmentX(Component.CENTER_ALIGNMENT);
		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				prime.right();
			}
		});
		right.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
		put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_RIGHT,0), "RightArrow_pressed");
		right.getActionMap().put("RightArrow_pressed", buttonRightPressed);
		panel.add(right);

		//layout the buttons with an extra box panel
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.X_AXIS));
		buttonpanel.add(left);
		buttonpanel.add(pick);
		buttonpanel.add(right);
		panel.add(buttonpanel);

		//add the down button and its events
		AbstractAction buttonDownPressed = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println(e.getActionCommand());
				//System.exit(0);
				prime.down();
			}
		};
		JButton down = new JButton("Down");
		down.setMaximumSize(buttonsize);
		down.setAlignmentX(Component.CENTER_ALIGNMENT);
		down.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				prime.down();
			}
		});
		down.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
		put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DOWN,0), "DownArrow_pressed");
		down.getActionMap().put("DownArrow_pressed", buttonDownPressed);
		panel.add(down);

		
		
		//add the prize icon in a random place
		PaintPrize icon = new PaintPrize(panel.getGraphics());
		Prize gift = new Prize(grid, randomInt(GameWorld.size), randomInt(GameWorld.size), Direction.NORTH, true, icon);


		//adding the good robot on a random position
		prime = new Optimus(grid, randomInt(GameWorld.size), randomInt(GameWorld.size), Direction.NORTH, gift, badRobot);
		prime.setSpeed(100);
		prime.setColor(Color.RED);
		
		//adding the bad robot on a random position
		badRobot = new BadRobot(grid, randomInt(GameWorld.size), randomInt(GameWorld.size), Direction.NORTH, prime);
		badRobot.setColor(Color.BLUE);
		prime.badRobot= badRobot;
		
		//finalise the frame
		frame.pack();
		frame.setSize(768, 902);
	}


	//allocate a new thread object and start it
	public static void startTheNewThread() {
		RobotUIComponents robot = new RobotUIComponents(grid);
		JButton start = robot.getStartStopButton();
		start.doClick();

		trobot = new Thread(badRobot);
		trobot.start();
	}


	//main method
	public static void main(String[] args) {
		frame = new JFrame("Catch the prize! - Easy");
		panel = new JPanel();
		frame. setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/Gift.png"));
		addEverything();
		startTheNewThread();

	}
}
