package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author vsokolov
 * @version $Id$
 * @since 0.1
 */
public class ArrayCharTest {
	
	@Test
	public void startsWithIsTrue() {
		ArrayChar hello = new ArrayChar("Hello");
		boolean result = hello.startsWith("He");
		assertThat(result, is(true));
	}
	
	@Test
	public void startsWithIsFalse() {
		ArrayChar hello = new ArrayChar("Hello");
		boolean result = hello.startsWith("Hi");
		assertThat(result, is(false));
	}
	
	@Test
	public void containsIsTrue() {
		ArrayChar hi = new ArrayChar("Hi");
		boolean result = hi.contains("Hello", "ell");
		assertThat(result, is(true));
	}
	
	@Test
	public void containsIsFalse() {
		ArrayChar hi = new ArrayChar("Hi");
		boolean result = hi.contains("Hello", "ext");
		assertThat(result, is(false));
	}
}