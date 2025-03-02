package myDeque;
import java.lang.reflect.Array;

public class Util {
	
	// Removes special characters and converts the String to lowercase
	private static String makeAlphanumericAndLowerCase(String s) {
		String newStr = "";
		for (char c : s.toCharArray()) {
			// Only add letters and numbers to the new String
			if (Character.isLetterOrDigit(c)) {
				newStr += c;
			}
		}
		
		return newStr.toLowerCase();
	}
	
	public static boolean isPalindrome(String s) {
		// Ensure all special characters are removed before running the method
		s = makeAlphanumericAndLowerCase(s);
		
		CircularDeque<Character> deque = new CircularDeque<>(s.length());
		for (char c : s.toCharArray()) {
			deque.addFront(c);
		}
		
		for (int i = 0; i < s.length(); i++) {
			char letter = deque.removeFront();
			if (letter != s.charAt(i)) {
				return false;
			}
		}
				
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> T[] slidingWindowMax(T[] data, int k) {
	    if (data == null || data.length == 0 || k <= 0) {
	        return (T[]) Array.newInstance(data.getClass().getComponentType(), 0);
	    }

	    int resultSize = data.length - k + 1;
	    T[] result = (T[]) Array.newInstance(data.getClass().getComponentType(), resultSize);

	    CircularDeque<T> deque = new CircularDeque<>(k);

	    for (int i = 0; i < data.length; i++) {
	        // Remove elements not in the current window
	        if (!deque.isEmpty() && i >= k && deque.peekFront().equals(data[i - k])) {
	            deque.removeFront();
	        }

	        // Remove all elements smaller than the current element
	        while (!deque.isEmpty() && deque.peekRear().compareTo(data[i]) <= 0) {
	            deque.removeRear();
	        }

	        deque.addRear(data[i]);

	        // If the window has reached size k, add the current max to the result
	        if (i >= k - 1) {
	            result[i - k + 1] = deque.peekFront();
	        }
	    }

	    return result;
	}
}


