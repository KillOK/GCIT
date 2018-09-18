package com.gcit.september.day1.task1;

import java.util.Scanner;

/**
 * 
 * @author Flex
 *
 */
public class Main {

	public static void main(String[] args) {
		/**
		 * Starting ui menu which would as if we want to play
		 */
		Menu.menu();
		System.out.println("Good bue!"); // Just to be nice
	}

}

/**
 * 
 * @author Flex
 * 
 *         Just to ask if we want to play...
 * 
 */

class Menu {
	static Scanner scn = new Scanner(System.in);

	/**
	 * While you enter answer yes, the game would start again
	 */
	static void menu() {
		System.out.println("Do you want to play?\nPrint yes to play...");
		// s - var for customer input storage
		String s = scn.next();
		while (s.equals("yes")) {
			new Game().start();
			menu();
			break;
		}

		scn.close();
	}
}

/**
 * 
 * @author Flex
 *
 */
class Game {

	// count tries
	int count;

	// random number, which you should guess
	int magicNumber;

	// your choice of number
	int yourNumber;

	Scanner sc = new Scanner(System.in);
	
	/**
	 * Empty constuctor for primary initialization of variables
	 */
	public Game() {
		//var for tries count
		count = 0;
		magicNumber = (int) (Math.random() * 1000);
	}
	
	/**
	 * Starts the game
	 */
	public void start() {
		do {
			count++;
			enterNumber();
			if (yourNumber >= magicNumber - 10 && yourNumber <= magicNumber + 10) {
				System.out.println("You win, your number: " + yourNumber + " MagicNumber: " + magicNumber);
				break;
			} else {
				System.out.println("You lose, your number: " + yourNumber + " MagicNumber: " + magicNumber
						+ " \n counts left:" + (5 - count));
			}
		} while (count < 5);

	}

	/**
	 * function, which validate and initialize user number input 
	 * should be from 1 to 1000
	 */
	public void enterNumber() {
		System.out.println("Guess number from 1 to 1000...");
		if (sc.hasNextInt()) {
			yourNumber = sc.nextInt();
			if (yourNumber > 1000 || yourNumber < 0) {
				System.out.println("Number should be => 0 and <=1000");
				enterNumber();
			}
		} else {
			System.out.println("You should enter number from 1 to 1000");
			enterNumber();
		}
	}

}