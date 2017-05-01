/**
 * 
 */
package com.braintree.creditcard.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.braintree.creditcard.report.SummaryGenerator;

/**
 * This is the main class that accepts input in the form of command line
 * arguments or stdin and delegates to processing.
 * 
 * @author Subashini
 *
 */
public class CardProviderService {

	/**
	 * @param args
	 */

	private static final String ADD = "Add";
	private static final String CHARGE = "Charge";
	private static final String CREDIT = "Credit";

	public static void main(String[] args) {

		CardProviderService manager = new CardProviderService();
		manager.manageCards(args);

		// Tom 4111111111111111
		// Lisa 5454545454545454
		// Quincy 1234567890123456

	}

	CardProcessor processor = new CardProcessor();
	SummaryGenerator gen = new SummaryGenerator();

	public void manageCards(String[] args) {
		if (args != null && args.length > 0 && args[0] != null && args[0].endsWith(".txt")) {
			processFile(args[0]);
		} else {
			boolean execute = true;
			System.out.println(
					"Please enter different operations to Add, Charge, Credit in each line. Please type EXIT to process and print summary");
			List<String> inputLines = new ArrayList<String>();
			Scanner scan = new Scanner(System.in);
			while (execute) {
				String line = scan.nextLine();
				if (line.equalsIgnoreCase("exit")) {
					execute = false;
				} else {
					inputLines.add(line);
				}
			}
			scan.close();
			for (String line : inputLines) {
				processLine(line);
			}
//			System.out.println(inputLines);
		}
		System.out.println(gen.generateSummarybyName(processor.getCards()));
	}

	public void processFile(String inputFile) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fileInput = new FileInputStream(inputFile);
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(fileInput));
			try {
				String line;
				while ((line = buffReader.readLine()) != null) {
//					System.out.println(line);
					processLine(line);
				}
				fileInput.close();
				buffReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to read the file.");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");

		}

	}

	public void processLine(String line) {

		String[] tokens = line.split(" ");
		if (tokens != null && tokens.length > 0) {
			if (ADD.equalsIgnoreCase(tokens[0])) {
				processor.addCreditCard(tokens);

			} else if (CHARGE.equalsIgnoreCase(tokens[0])) {
				processor.chargeAmount(tokens);

			} else if (CREDIT.equalsIgnoreCase(tokens[0])) {
				processor.creditAmount(tokens);
			}
		}
	}

}
