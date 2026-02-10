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
	private static String getUserInput(Scanner sc, String prompt) {
		System.out.println(prompt);
		return sc.nextLine();
	}
	
	// Helper method to read int in range
	private static int readIntInRange(Scanner sc, String prompt, int min, int max) {
		
		Integer value = null;
		
		// Steps to validate input/prevent crash
		do {
			
			String userInput = getUserInput(sc, prompt);
			
			try {
				value = Integer.parseInt(userInput);
				if(value < min || value > max) {
					throw new OutOfRangeException(
						"Error: Enter a value between " + min + " and " + max);
				}
			}
			catch(NumberFormatException e ) {
				System.out.println("Invalid Input: Please enter an integer");
			}
			catch(OutOfRangeException e) { // Custom exception
				value = null;
				System.out.println(e.getMessage());
			}
			catch(Exception e) {
				System.out.println("An error occurred");
			}
		} while(value == null);
	
		return value; // Return validated value
	}
	 
	// Helper method to read double
	private static double readDoubleInRange(Scanner sc, String prompt, double min, double max) {
		
		Double value = null;
				
				// Steps to validate input/prevent crash
				do {
					
					String userInput = getUserInput(sc, prompt);
					
					try {
						value = Double.parseDouble(userInput);
						if(value < min || value > max) {
							throw new OutOfRangeException(
								"Error: Enter a value between " + min + " and " + max);
						}
					}
					catch(NumberFormatException e ) {
						System.out.println("Invalid Input: Please enter a number");
					}
					catch(OutOfRangeException e) {
						value = null;
						System.out.println(e.getMessage());
					}
					catch(Exception e) {
						System.out.println("An error occurred");
					}
				} while(value == null);
			
				return value;
			}
		
	// Helper method to read string input MIGHT NOT NEED
	private static String readString(Scanner sc, String prompt) {
		return getUserInput(sc, prompt);
	}
	
	// Helper method to read compounding choice
	private static int readCompoundingChoice(Scanner sc) {
		
		int choice = readIntInRange(sc, "Choose your compounding frequency (1-3):", 1, 3);
		
		return choice;
	}
	
	
	// Helper method to run simulation
	private static void runSimulation(Scanner sc) {
		
		
		
	}
	
	// Helper method to let user choose to run simulation again
	private static Boolean askRunAgain(Scanner sc) {
		
		
		// return true or false
	}
	
	// Custom exception
	static class OutOfRangeException extends Exception {
		private static final long serialVersionUID = 1L;
		public OutOfRangeException() {
			super();
		}
		public OutOfRangeException(String message) {
			super(message);
		}
		public OutOfRangeException(Throwable cause) {
			super(cause);
		}
		public OutOfRangeException(String message, Throwable cause) {
			super(message, cause);
		}
}