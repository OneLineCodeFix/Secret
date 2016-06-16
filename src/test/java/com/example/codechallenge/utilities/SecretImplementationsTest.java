package com.example.codechallenge.utilities;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class SecretImplementationsTest {
	
	@Test
	public void testSecretSuccess1() {
		assertEquals(SecretImplementations.secretSuccess1(1),1);
	}
	
	@Test
	public void testSecretFailure1() {
		assertNotEquals(SecretImplementations.secretFailure1(1),1);
	}
	
	@Test
	public void testSecretFailure2() {
		assertNotEquals(SecretImplementations.secretFailure2(1),1);
	}
	
	@Test
	public void testSecretFailure3() {
		assertNotEquals(SecretImplementations.secretFailure3(1),1);
	}
	
	@Test
	public void testEvilSecretSuccess() {
		assertEquals(SecretImplementations.secretFailureEvil(13),13);
	}
	
	@Test
	public void testEvilSecretFailure() {
		assertNotEquals(SecretImplementations.secretFailureEvil(23),23);
	}
	
}
