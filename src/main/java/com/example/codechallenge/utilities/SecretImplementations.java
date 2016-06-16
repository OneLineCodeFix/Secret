package com.example.codechallenge.utilities;

public class SecretImplementations {

	public static int secretSuccess1(int i) {
		return i;
	}
	
	public static int secretFailure1(int i) {
		return i-1;
	}
	
	public static int secretFailure2(int i) {
		return i/2;
	}
	
	public static int secretFailure3(int i) {
		return i+3;
	}

	/**
	 * This method is evil because you think it satisfies the additive property until you get to 23
	 * 
	 * @param i
	 * @return
	 */
	public static int secretFailureEvil(int i) {
		if(i == 23) {
			return 0;
		}
		return i;
	}
	
}
