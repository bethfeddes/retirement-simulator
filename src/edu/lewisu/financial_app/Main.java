package edu.lewisu.financial_app;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		// Display welcome at start
		System.out.println("Welcome to the Retirement Growth Simulator");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("This simulator will simulate the growth of a retirement account over"
				+ "time, \nusing age, annual rate, compounding type, and annual contribution.");
		System.out.println("------------------------------------------------------------------------");
		
		do {
			// Collect input
			int currentAge = readIntInRange(sc, "Current Age: ", 18, 100);
			int retirementAge = readIntInRange(sc, "Retirement Age: ", currentAge + 1, 100);
			int currentBalance = readIntInRange(sc,"Current Balance: ", 0, Integer.MAX_VALUE);
			double annualInterestRate = readDoubleInRange(sc, "Annual Interest Rate (APR%): ", 0, 30);
			System.out.println("Compounding Frequency");
			System.out.println("1. Annually");
			System.out.println("2. Monthly");
			System.out.println("3. Daily");
			int compoundingChoice = readCompoundingChoice(sc);
			double annualContribution = readDoubleInRange(sc, "Annual Contribution (Year 1): ", 0.0, Double.MAX_VALUE);
			double annualContributionIncrease = readDoubleInRange(sc, "Annual Contribution Increase: ", 0, 20);
			
			runSimulation(currentAge, retirementAge, currentBalance, annualInterestRate, compoundingChoice, annualContribution, 
					annualContributionIncrease);
			
			System.out.println("\n------------------------------------------------------------------------");
		
		} while(askRunAgain(sc)); // Allow user to run another simulation
	}

	// Simple helper method for getting input
	// Helper to get user input
	private static String getUserInput(Scanner sc, String prompt) {
		System.out.println(prompt);
		return sc.nextLine();
	}
	
	// Helper method to read/validate an integer within a range
	// Helper method to read integer in range
	private static int readIntInRange(Scanner sc, String prompt, int min, int max) {
		
		Integer value = null;
		
		// Steps to validate input/prevent crash
		do {
			
			String userInput = getUserInput(sc, prompt);
			
			try {
				value = Integer.parseInt(userInput);
				if(value < min || value > max) {
					throw new OutOfRangeException(
						"Error: Out of range.");
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
	
	// Helper method to read/validate a double within a range
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
								"Error: Out of range.\n");
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
		
	// Helper method to read compounding choice
	private static int readCompoundingChoice(Scanner sc) {
		
		int choice = readIntInRange(sc, "Choose (1-3): ", 1, 3);
		
		return choice;
	}

	// Helper method to run the logic/calculations/display table of simulation
	// Helper method to run simulation
	private static void runSimulation(int age, int retirement, int initial, double annInterestRate, int compoundOption, double annualContribution,
			double annualContributionIncrease) {
		
		// Display
		System.out.println("\nYear-by-Year Projection");
		System.out.println("------------------------------------------------------------------------");
		System.out.printf("%-4s | %-15s | %-15s | %-15s | %-15s", "Age", "Start Balance", "Contributions", "Interest Earned", "End Balance");
		System.out.println("\n------------------------------------------------------------------------");
		
		int years = retirement - age;
		double balance = initial;
		double totalContributions = 0;
		double totalInterest = 0;
		double endBalance = 0;
		int periodsPerYear = 0;
		
		// Cases for compounding logic
		switch(compoundOption) {
			case 1: periodsPerYear = 1; break; // Annual
			case 2: periodsPerYear = 12; break; // Monthly
			case 3: periodsPerYear = 365; break; // Daily
		}
		
		double r_period = (annInterestRate/100) / periodsPerYear; // Per-period rate
		
		for (int i = 0; i < years; i++) { // Loop through years
			double startBalance = balance;
			double contributionsThisYear = 0;
			
			for (int p = 0; p < periodsPerYear; p++) { // Loop through periods
				
				double periodContribution = annualContribution / periodsPerYear;
				balance += periodContribution;
				contributionsThisYear += periodContribution;
				balance = (balance)*(1 + r_period); // Apply interest
			}
			
			endBalance = balance;
			double interestEarned = endBalance - startBalance - contributionsThisYear;

			totalContributions += contributionsThisYear; // Track total contributions
			totalInterest += interestEarned; // Track total interest earned
			
			System.out.printf("%-4d | %-15.2f | %-15.2f | %-15.2f | %-15.2f\n", age += 1, startBalance, annualContribution, interestEarned, endBalance);
	
			annualContribution = annualContribution*(1 + annualContributionIncrease/100);
			
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Summary");
		System.out.println("-------");
		System.out.printf("Total Contributions: $%.2f", totalContributions);
		System.out.printf("\nTotal Interest Earned: $%.2f", totalInterest);
		System.out.printf("\nEnding Balance at Age %d: $%.2f", age, endBalance);
	}
	
	// Helper get Y/N answer from user
	// Helper method to let user choose to run simulation again
	private static Boolean askRunAgain(Scanner sc) {
		
		do {
			
			String value = getUserInput(sc, "\nRun the simulation again? (enter Y or N)")
				.toUpperCase(); // Normalize input
			
			if (value.equals("Y")) {
				return true;
			}
			else if (value.equals("N")) {
				return false;
			}
			else {
				System.out.println("Invalid input. Please enter Y or N.");
			}
			
		} while(true);
		
	}
	
	// Custom exception
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
}