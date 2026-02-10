package edu.lewisu.financial_app;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to the Retirement Growth Simulator");
		System.out.println("------------------------------------------");
		System.out.print("This simulator will simulate the growth of a retirement account over"
				+ "time, using age, annual rate, \ncompounding type, and annual contribution.");
		
	}

	// Helper to get user input
	private static String getUserInput ()
	
	// Helper method to read int
	private static int readIntInRange(Scanner sc, String prompt, int min, int max) {
		
		Integer value = null;
		
		do {
			
			System.out.println(prompt);
			value = sc.nextInt();
			
			// 
			if (value >= min && value <= max) { 
				
				
				
			}
			
			else {
				
				System.out.println("Please enter an integer within range.");
				
			}
		
		} while(value == null);
	
		return intInput;
	}
	
	// Helper method to read double
	private static void readDoubleInRange(Scanner sc, String prompt, double min, double max) {
		
		
		
	}
	
	// Helper method to read compounding choice
	private static int readCompoundingChoice(Scanner sc) {
		
		
		return 0; // Change later
	}
	
	// Helper method to run simulation
	private static void runSimulation(Scanner sc) {
		
		
		
	}
	
	// Helper method to let user choose to run simulation again
	private static Boolean askRunAgain(Scanner sc) {
		
		
		// return true or false
	}
	
}