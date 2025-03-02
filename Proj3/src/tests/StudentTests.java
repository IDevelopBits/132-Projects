package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import myDeque.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentTests {
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeCapacityThrowsException() {
		new CircularDeque<>(-5); // Should throw IllegalArgumentException
	}

	@Test
	public void testAddFront() {
		CircularDeque<String> deque = new CircularDeque<>(4);
		deque.addFront("dog");
		deque.addFront("cat");

		assertEquals("Front element should be cat", "cat", deque.peekFront());
		assertEquals("Deque size should be 2", 2, deque.size());

		deque.addFront("rabbit");
		String expected = "Circular Deque [deque=[null, rabbit, cat, dog], "
				+ "front=1, rear=0, size=3, capacity=4]";
		
		assertTrue(deque.toString().equals(expected));
	}

	/**
	 * Check that an exception is thrown when attempting to insert into a full
	 * deque.
	 */
	@Test(expected = IllegalStateException.class)
	public void testAddMethodsException() {
		CircularDeque<Person> deque = new CircularDeque<>(2);
		deque.addRear(new Person("John", 0));
		deque.addRear(new Student("Jake", 1, 3.5));
		// This should throw an IllegalStateException
		deque.addFront(new Student("Jack", 2, 2.34)); 
	}

	/**
	 * Tests mixed insertions and removes to verify that removeRear() and
	 * removeFront() regardless of the front and rear values.
	 */
	@Test
	public void testRemoveMethods() {
		CircularDeque<Integer> deque = new CircularDeque<>(5);
		deque.addFront(100);
		deque.addFront(300);
		deque.addRear(250);
		deque.addFront(500);
		
		deque.removeFront();
		deque.removeRear();
		deque.removeRear();
		
		String expected = "Circular Deque [deque=[250, null, 500, 300, 100], "
				+ "front=3, rear=4, size=1, capacity=5]";

		assertTrue(deque.toString().equals(expected));
	}

	/**
	 * Test an empty deque and ensure that exceptions are thrown when attempting to
	 * remove elements.
	 */
	
	@Test(expected = IllegalStateException.class)
	public void testEmptyDequeRemovals() {
		CircularDeque<String> deque = new CircularDeque<>(3);
		deque.removeRear();
		deque.addFront("Nothing's gonna be added");
	}

	/**
	 * Test palindrome functionality with various formats including special
	 * characters and spaces.
	 */
	@Test
	public void testPalindromeVariations() {
		// Mixed case should still pass
		assertTrue(Util.isPalindrome("lEveL"));
		
		assertFalse(Util.isPalindrome("World"));

		// Palindrome with punctuation and spaces
		assertTrue(Util.isPalindrome("Eva, can I see bees in a cave?"));
		assertFalse(Util.isPalindrome("Jy'Mere"));
	}

	/**
	 * Test the sliding window max function with a different set of integers.
	 */
	@Test
	public void testSlidingWindowMaximum() {
		Integer[] nums = { 1, 2, 7, 3, 8, 5, 10, 6 };
		int k = 3;

		Integer[] expected = { 7, 7, 8, 8, 10, 10 };
		Integer[] result = Util.slidingWindowMax(nums, k);
		assertArrayEquals(expected, result);

		nums = new Integer[] { -1, 2, -3, 4, 6, 8, 3, -2 };
		expected = new Integer[] { 2, 4, 6, 8, 8, 8 };
		
		result = Util.slidingWindowMax(nums, k);
		assertArrayEquals(expected, result);
	}
}