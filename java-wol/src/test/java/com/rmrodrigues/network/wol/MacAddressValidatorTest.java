/**
 * 
 */
package com.rmrodrigues.network.wol;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rmrodrigues.network.wol.validator.MacAddressValidator;

/**
 * @author rmrodrigues
 * 
 */
public class MacAddressValidatorTest {

	@Test
	public void validIEEEDoubleDotMACAddress() {
		assertEquals(true, MacAddressValidator.validate("11:22:33:AA:BB:CC"));
	}

	@Test
	public void invalidIEEEDoubleDotMACAddressUpperCase() {
		assertEquals(false, MacAddressValidator.validate("11:22:33:AA:BB:CG"));
	}

	@Test
	public void validIEEEDoubleDotMACAddressUpperCase() {
		assertEquals(true, MacAddressValidator.validate("11:22:33:aa:bb:cc"));
	}

	@Test
	public void invalidIEEEDoubleDotMACAddress() {
		assertEquals(false, MacAddressValidator.validate("11:22:33:AA:bb:CG"));
	}

	@Test
	public void validIEEEHyphenMACAddress() {
		assertEquals(true, MacAddressValidator.validate("11-22-33-AA-BB-CC"));
	}

	@Test
	public void invalidIEEEHyphenMACAddressUpperCase() {
		assertEquals(false, MacAddressValidator.validate("11-22-33-AA-BB-CG"));
	}

	@Test
	public void validIEEEHyphenMACAddressUpperCase() {
		assertEquals(true, MacAddressValidator.validate("11-22-33-aa-bb-cc"));
	}

	@Test
	public void invalidIEEEHyphenMACAddress() {
		assertEquals(false, MacAddressValidator.validate("11-22-33-AA-bb-CG"));
	}
	
	@Test
	public void validIEEE2DotMACAddress() {
		assertEquals(true, MacAddressValidator.validate("1122.33AA.BBCC"));
	}

	@Test
	public void invalidIEEE2DotMACAddressUpperCase() {
		assertEquals(false, MacAddressValidator.validate("1122.33AA.BBCG"));
	}

	@Test
	public void validIEEE2DotMACAddressUpperCase() {
		assertEquals(true, MacAddressValidator.validate("1122.33aa.bbcc"));
	}

	@Test
	public void invalidIEEE2DotMACAddress() {
		assertEquals(false, MacAddressValidator.validate("1122.33AA.bbCG"));
	}

}
