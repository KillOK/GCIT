package com.gcit.september.day1.task2;

import java.util.Scanner;
/**
 * 
 * @author Flex
 * 
 * Game, in which players should get less then half of chips from the pile per one step,
 * player, who will have the even value of the chips in the end win...
 * Good luck!
 * 
 */
public class Ex2ChipsGame {

	public static void main(String[] args) {
		new MenuUI().start();
	}

}

/**
 * 
 * @author Flex
 *	UI menu for Game start and continue...
 */
class MenuUI {
	static final String border = "******************************************************************************";
	Scanner scn = new Scanner(System.in);
	String s ;
	
	public void start() {
		greetings();
		menu();
		farewell();
	}
	/**
	 * Starts the game again while user reply yes
	 */
	void menu() {
		System.out.println(border);
		System.out.println("Do you want to play?\nPrint yes to play...");
		if(scn.hasNext()) {
			s = scn.next();
			while (s.toLowerCase().equals("yes")) {
				new Game().start();
				menu();
			}
		}
	}
	
	public void greetings() {
		System.out.println(border);
		System.out.println("Hello!");
	}
	
	public void farewell() {
		System.out.println(border);
		System.out.println("Good bue!");
		System.out.println(border);
	}
}


class Game {
	
	static final String border = "******************************************************************************";

	private Player[] players = { new Player(), new Player() };	// Array of players
	
	int buffer;					// count tries
	int magicNumber;			// number of chips in the pile 								(depends from user input)
	int yourNumber;				// player choice 			   								(depends from user input)
	int minPileCapacity = 3;	// minPileCapacity											(depends from user input)
	int maxPileCapacity = 999;	// maxPileCapacity											(depends from user input)
	int minPlayerChoice = 1;	// minPlayerChoice											(depends from user input)
	int maxPlayerChoice;		// maxPlayerChoice - middle value of the pile				(depends from user magicNumber)

	Scanner sc = new Scanner(System.in);

	public Game() {
		for (Player p : players) {
			System.out.println("Enter name of " + p.getPlayerId());
			p.setPlayerName(setNames());
			System.out.println(p.getPlayerId() + " name: " + p.getPlayerName());
		}
		magicNumber = enterInitialChipsNumber();
		System.out.println(border);
	}

	public String setNames() {
		String name = null;
		if (sc.hasNextLine()) {
			name = sc.nextLine();
			for (Player player : players) {
				if (player.getPlayerName() != null)
					if (player.getPlayerName().toLowerCase().equals(name.toLowerCase())) {
						System.out.println("Both players cannot be named " + name + "\nEnter name of Player2");
						name = setNames();
						break;
					}
			}
		}
		return name;
	}

	public void start() {
		while (magicNumber > 0) {
			for (Player player : players) {
				if (magicNumber > 0) {
					for (Player p : players) {
						System.out.println(p.getPlayerName() + " has " + p.getCookies() + " chips");
					}
					System.out.println("It's your turn " + player.getPlayerName() + " \n" + "There are " + magicNumber
							+ " chips remaining. You may take any number of chips from 1 to "
							+ (((magicNumber - 1) / 2) + 1) + "\nHow many will you take, "+player.getPlayerName()+"?");
					enterNumber();
					magicNumber -= yourNumber;
					player.setCookies(player.getCookies() + yourNumber);
					System.out.println(border);
					
				}
			}
		}
		finish();
	}

	public void finish() {
		for (Player p : players) {
			System.out.println(p.getPlayerName() + " has " + p.getCookies() + " chips");
		}
		for (Player p : players) {
			if (p.getCookies() % 2 == 0) {
				System.out.println(p.getPlayerName() + " has even  number of chips: " + p.getCookies());
				System.out.println(p.getPlayerName() + " wins!");
			}
		}
		Player.setId(0);
	}
	
	/**
	 * User input validation of initial value of cookies in the pile
	 * @return the initial value of the cookis
	 */
	public int enterInitialChipsNumber() {
		 int chipsNumber = 0;

		System.out.println("How many chips does the initial pile contain?");
		if(sc.hasNext()) {
			if (sc.hasNextInt()) {
				chipsNumber = sc.nextInt();
				if (chipsNumber > maxPileCapacity || chipsNumber < minPileCapacity) {
					System.out.println("You have to start with at least 3 chips and less then "+maxPileCapacity);
					chipsNumber = enterInitialChipsNumber();
				}
				if (chipsNumber % 2 == 0) {
					System.out.println("The number should be odd");
					chipsNumber = enterInitialChipsNumber();
				}
			}else{
				System.out.println("You should enter number from 1 to 1000");
				sc.next();
				chipsNumber = enterInitialChipsNumber();
			}
		}
		return chipsNumber;
	}

	/**
	 * Validate user input for value of cookies, which player can get per one step
	 * Usage of global variable in this methods is bad practice, but I hope in so small code part it's not a big problem...
	 */
	public void enterNumber() {
		System.out.println("Player remove between 1 and " + (((magicNumber - 1) / 2) + 1) + " from the pile");
		if (sc.hasNextInt()) {
			yourNumber = sc.nextInt();
			if ((yourNumber > ((magicNumber - 1) / 2) + 1) || yourNumber < 1) {
				System.out.println("Number should be between 1 and " + (((magicNumber - 1) / 2) + 1));
				enterNumber();
			}
		} else {
			System.out.println("Number should be between 1 and " + (((magicNumber - 1) / 2) + 1));
			sc.next();
			enterNumber();
		}
	}
}

/**
 * 
 * @author Flex
 *	Player description
 *	var id{@link Player#id} - counts players
 *	var PlayerId{@link Player#PlayerId} - String PlayerId
 *	var playerName{@link Player#playerName} - name from user input
 *	var cookies{@link Player#cookies} - value of cookies, which the user have
 */
class Player {
	private static int id = 0;

	public static void setId(int id) {
		Player.id = id;
	}
	
	/**
	 * Initialize Ids
	 */
	public Player() {
		id++;
		PlayerId = "Player" + id;
	}

	private String PlayerId;
	private String playerName;
	public int cookies;
	
	
	//geters and setters...
	public String getPlayerId() {
		return PlayerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getCookies() {
		return cookies;
	}

	public void setCookies(int cookies) {
		this.cookies = cookies;
	}

	public static int getId() {
		return id;
	}

}