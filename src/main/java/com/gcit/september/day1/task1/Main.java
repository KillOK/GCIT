package com.gcit.september.day1.task1;

import java.util.Scanner;

public class Main {
	
	
	
	public static void main(String[] args) {
		Menu.menu();
		System.out.println("Good bue!");
	}

}

	class Menu{
		static Scanner scn = new Scanner(System.in);
		static void menu() {
			System.out.println("Do you want to play?\nPrint yes to play...");
			String s = scn.next();
			while(s.equals("yes")) {
				new Game().start();
				menu();
				break;
			}
			
			scn.close();
		}
	}

	class Game{
		
		int count;
		
		int magicNumber;
		
		int yourNumber;
		
		Scanner sc = new Scanner(System.in);
		
		public Game() {
			count = 0;
			magicNumber = (int)(Math.random() * 1000);
		}
		
		public void start() {
			do {
				count++;
				enterNumber();
				if(yourNumber>=magicNumber-10&&yourNumber<=magicNumber+10) {
					System.out.println("You win, your number: "+ yourNumber+ " MagicNumber: "+ magicNumber);
					break;
				}else {
					System.out.println("You lose, your number: "+ yourNumber+ " MagicNumber: "+ magicNumber+" \n counts left:" + (5-count));
				}
			}while(count<5);
			
		}
		
		public void enterNumber() {
			System.out.println("Guess number from 1 to 1000...");
			if(sc.hasNextInt()) {
				yourNumber = sc.nextInt();
				if(yourNumber>1000||yourNumber<0) {
					System.out.println("Number should be => 0 and <=1000");
					enterNumber();
				}
			}else {
				System.out.println("You should enter number from 1 to 1000");
				enterNumber();
			}
		}
		
		
	}