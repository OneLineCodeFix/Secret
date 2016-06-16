package com.example.codechallenge;
import java.util.List;

import com.example.codechallenge.exceptions.SecretConfigurationException;
import com.example.codechallenge.utilities.Utilities;

public class Main {

	public static void main(String[] args) {
				
		int number = 0;
		if(args.length == 0) {
			System.out.println("Please provide a valid integer input (e.g. 1234).");
			System.exit(-1);
		}
		try {
			number = Integer.parseInt(args[0]);
		} catch (NumberFormatException nfe) {
			System.out.println("Please provide a valid integer input (e.g. 1234).");
			System.exit(-1);
		}

		List<Integer> primesList = Utilities.calculatePrimesList(number);
		if(primesList.isEmpty()) {
			System.out.println("Unable to evaluate secret function. Please provide a larger input.");
			System.exit(-1);
		}

		try {
			String result = ( Utilities.isAdditivePropertySatisfied(primesList) ? "secret was additive" : "secret was not additive");
			System.out.println(result);
		} catch (SecretConfigurationException e) {
			e.printStackTrace();
		}
	}
}
