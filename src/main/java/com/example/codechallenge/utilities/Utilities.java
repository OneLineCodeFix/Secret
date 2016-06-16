package com.example.codechallenge.utilities;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.example.codechallenge.exceptions.SecretConfigurationException;

public class Utilities {
	
	private static final String SECRET_METHODNAME = "secret.methodname";
	private static final String SECRET_CLASSNAME = "secret.classname";
	
	private static Properties _properties;
	
	/**
	 * Calculates and returns all primes less than the integer passed in using the Sieve of 
	 * Eratosthenes
	 * @param number
	 * @return
	 */
	public static List<Integer> calculatePrimesList(int number) {
		//Use a map for fast lookups later in the sieve
		//Use a LinkedHashMap to preserve insertion order.
		Map<Integer,Boolean> primes = new LinkedHashMap<Integer,Boolean>();
		//Populate the map with every number between 2 and the parameter passed in.
		for(int i=2; i< number;i++) {
			primes.put(i, Boolean.TRUE);
		}
		
        //Sieve of Eratosthenes
		//iterate up to the square root
		for (int i = 2; i*i < number; i++) {
            //If the base is in the map, remove all its products
			if (primes.containsKey(i)) {
                //remove all the products of i*j
				for (int j = i; i*j <= number; j++) {
                    primes.remove(i*j);
                }
            }
        }
		
		//Make a list of the remaining keys
		return  new ArrayList<Integer>(primes.keySet());
	}

	/**
	 * Returns isAdditivePropertySatisfied after retrieving the className and secretName
	 * 
	 * @param primeList
	 * @return
	 * @throws SecretConfigurationException
	 */
	public static boolean isAdditivePropertySatisfied(List<Integer> primeList) throws SecretConfigurationException 
	{
		try {
			return isAdditivePropertySatisfied(primeList, getSecretClassName(), getSecretMethodName());
		} catch (IOException e) {
			throw new SecretConfigurationException("Unable to build secret() method implementation - check config.properties", e);
		}
	}
	
	public static boolean isAdditivePropertySatisfied(List<Integer> primeList, String className, String methodName) throws SecretConfigurationException 
	{	
		// Use reflection to instantiate the secret class and method
		Method method = null;
		try {
			Class<?> clazz = Class.forName(className);
			method = clazz.getMethod(methodName, Integer.TYPE);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			throw new SecretConfigurationException("Unable to build secret() method implementation - invalid class or method name", e);
		}
		
		//For every combination of primes
		for (int i = 0; i < primeList.size(); i++) {
			for (int j = i; j < primeList.size(); j++) {
				
				Integer x = primeList.get(i);
				Integer y = primeList.get(j);
	
				// Calculate results
				int xReturn;
				int yReturn;
				int xyReturn;
				try {
					xReturn = (Integer) method.invoke(null, x);
					yReturn = (Integer) method.invoke(null, y);
					xyReturn = (Integer) method.invoke(null, x + y);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new SecretConfigurationException("Unable to invoke secret() method - ensure the method is public and static", e);
				}
	
				//Evaluate additive property of the secret function
				if (xyReturn != (xReturn + yReturn)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static String getSecretClassName() throws IOException {
		return getProperty(SECRET_CLASSNAME);
	}
	
	private static String getSecretMethodName() throws IOException {
		return getProperty(SECRET_METHODNAME);
	}
	
	/**
	 * Tries to get the property by the given name from System properties first and subsequently 
	 * from  the included config.properties file 
	 * @param name Name of the property
	 * @return The property
	 * @throws IOException
	 */
	private static String getProperty(String name) throws IOException {
		//Try to get system property first
		String systemProperty = System.getProperty(name);
		if(systemProperty != null && systemProperty.length()>0) {
			return systemProperty;
		}
		
		//Default to packaged config
		//Only go to the file system once
		if(_properties == null) {
			_properties = new Properties();
			InputStream propertiesStream = Utilities.class.getClassLoader().getResourceAsStream("config.properties");
			_properties.load(propertiesStream);
		}
		
		return _properties.getProperty(name);
	}
}
