package tests;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


import myDeque.*;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PublicTests {
	
	
    /*---------Deque Tests -------------------------*/
     
	 /**
     * Test that constructing a CircularDeque with a capacity of 0 throws an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionForZeroCapacity() {
        new CircularDeque<>(0); // This should throw an exception
    }
   
     /**
      * Test the addFront() method.
      * Verifies that elements are added to the front of the deque.
      */
     @Test
     public void testAddFront() {
    	 
    	 CircularDeque<Integer> deque = new CircularDeque<>(5);
         deque.addFront(10); // Add 10 to the front
         deque.addFront(20); // Add 20 to the front
         assertEquals("Front element should be 20", (Integer) 20, deque.peekFront()); // Verify the front element
         assertEquals("Deque size should be 2", 2, deque.size()); // Verify the size of the deque
         deque.addFront(30); // Add 30 to the front
         assertTrue(TestsSupport.isCorrect("pubTest01.txt", deque.toString()));
     
     }
     
     /**
      * Test that an IllegalStateException is thrown when trying to add an element to a full deque.
      */
     @Test(expected = IllegalStateException.class)
     public void testAddRearThrowsExceptionWhenFull() {
         CircularDeque<Integer> deque = new CircularDeque<>(3); // Capacity of 3
         deque.addRear(10);
         deque.addRear(20);
         deque.addRear(30);
         // Now the deque is full, so adding another element should throw an exception
         deque.addRear(40); // This should throw an IllegalStateException
     }

     /**
      * Test the addRear() method.
      * Verifies that elements are added to the rear of the deque.
      */
     @Test
     public void testAddRear() {
         CircularDeque<Integer> deque = new CircularDeque<>(5);
         deque.addRear(10); // Add 10 to the rear
         deque.addRear(20); // Add 20 to the rear
         assertEquals("Rear element should be 20", (Integer) 20, deque.peekRear()); // Verify the rear element
         assertEquals("Deque size should be 2", 2, deque.size()); // Verify the size of the deque
         deque.addRear(30); // Add 30 to the rear
         assertTrue(TestsSupport.isCorrect("pubTest02.txt", deque.toString())); // Check if the string representation is correct
     }
     
     
     
     /**
      * Test the removeFront() method.
      * Verifies that elements can be removed from the front of the deque, 
      * and that add and remove operations work when the deque is full.
      */
     @Test
     public void testRemoveFront() {
         CircularDeque<Integer> deque = new CircularDeque<>(5);
         
         // Alternately add elements to the front and rear
         deque.addFront(10); // Add 10 to the front
         deque.addRear(20);  // Add 20 to the rear
         deque.addFront(30); // Add 30 to the front
         deque.addRear(40);  // Add 40 to the rear
         deque.addFront(50); // Add 50 to the front
         
         // Verify size is at capacity
         assertEquals("Deque size should be 5", 5, deque.size());
         
         // Remove an element from the front
         Integer removedElement = deque.removeFront();
         assertEquals("Removed element should be 50", (Integer) 50, removedElement); // Verify the removed element
         assertEquals("Deque size should be 4", 4, deque.size()); // Verify the size of the deque
         
         // Add a new element to the rear
         deque.addRear(60);
         assertEquals("Deque size should be 5", 5, deque.size()); // Verify the size of the deque after addition
         
         // Remove another element from the front
         removedElement = deque.removeFront();
         assertEquals("Removed element should be 30", (Integer) 30, removedElement); // Verify the removed element
         assertEquals("Deque size should be 4", 4, deque.size()); // Verify the size of the deque
         
         // Check if the final state of the deque is correct
         assertTrue(TestsSupport.isCorrect("pubTest03.txt", deque.toString()));
     }
     
     /**
      * Test the removeRear() method.
      * Verifies that elements can be removed from the rear of the deque, 
      * and that add and remove operations work when the deque is full.
      */
     @Test
     public void testRemoveRear() {
         CircularDeque<Integer> deque = new CircularDeque<>(5);
         
         // Alternately add elements to the front and rear
         deque.addFront(10); // Add 10 to the front
         deque.addRear(20);  // Add 20 to the rear
         deque.addFront(30); // Add 30 to the front
         deque.addRear(40);  // Add 40 to the rear
         deque.addFront(50); // Add 50 to the front
         
         // Verify size is at capacity
         assertEquals("Deque size should be 5", 5, deque.size());
         
         // Remove an element from the rear
         Integer removedElement = deque.removeRear();
         assertEquals("Removed element should be 40", (Integer) 40, removedElement); // Verify the removed element
         assertEquals("Deque size should be 4", 4, deque.size()); // Verify the size of the deque
         
         // Add a new element to the front
         deque.addFront(60);
         assertEquals("Deque size should be 5", 5, deque.size()); // Verify the size of the deque after addition
         
         // Remove another element from the rear
         removedElement = deque.removeRear();
         assertEquals("Removed element should be 20", (Integer) 20, removedElement); // Verify the removed element
         assertEquals("Deque size should be 4", 4, deque.size()); // Verify the size of the deque
         deque.removeRear();
         deque.removeRear();
         
         // Check if the final state of the deque is correct
         assertTrue(TestsSupport.isCorrect("pubTest04.txt", deque.toString()));
     }
     
     
     
 
     /**
      * Comprehensive test for CircularDeque.
      * Performs multiple add and remove operations to verify correctness.
      */
     @Test
     public void testComprehensiveOperations() {
         CircularDeque<Integer> deque = new CircularDeque<>(6);
         
         // Initial adds: Mix of front and rear
         deque.addFront(10);  // Front -> [10]
         deque.addRear(20);   // Rear -> [10, 20]
         deque.addFront(30);  // Front -> [30, 10, 20]
         deque.addRear(40);   // Rear -> [30, 10, 20, 40]
         deque.addFront(50);  // Front -> [50, 30, 10, 20, 40]
         deque.addRear(60);   // Rear -> [50, 30, 10, 20, 40, 60] (Full)

         // Verify size and content
         assertEquals("Deque should be full", 6, deque.size());
         assertEquals("Front element should be 50", (Integer) 50, deque.peekFront());
         assertEquals("Rear element should be 60", (Integer) 60, deque.peekRear());

         // Removing elements: Mix of front and rear
         assertEquals("Removed from front should be 50", (Integer) 50, deque.removeFront());
         assertEquals("Removed from rear should be 60", (Integer) 60, deque.removeRear());
         assertEquals("Removed from front should be 30", (Integer) 30, deque.removeFront());
         assertEquals("Removed from rear should be 40", (Integer) 40, deque.removeRear());

         // Remaining elements: [10, 20]
         assertEquals("Front element should be 10", (Integer) 10, deque.peekFront());
         assertEquals("Rear element should be 20", (Integer) 20, deque.peekRear());
         assertEquals("Deque size should be 2", 2, deque.size());

         // More add and remove operations
         deque.addFront(70);  // [70, 10, 20]
         deque.addRear(80);   // [70, 10, 20, 80]
         assertEquals("Front element should be 70", (Integer) 70, deque.peekFront());
         assertEquals("Rear element should be 80", (Integer) 80, deque.peekRear());

         // Clear the deque
         deque.clear();
         assertTrue("Deque should be empty after clearing", deque.isEmpty());

         // Ensure exception is thrown when removing from an empty deque
         try {
             deque.removeFront();
             fail("Expected IllegalStateException when removing from empty deque");
         } catch (IllegalStateException e) {
             assertEquals("Deque is empty.", e.getMessage());
         }

         try {
             deque.removeRear();
             fail("Expected IllegalStateException when removing from empty deque");
         } catch (IllegalStateException e) {
             assertEquals("Deque is empty.", e.getMessage());
         }

         // Final state check
         assertTrue(TestsSupport.isCorrect("pubTest05.txt", deque.toString()));
     }
     
     
     @Test
     public void testEdgeCase() {
         CircularDeque<Integer> deque = new CircularDeque<>(5);
         
         // add front
         deque.addFront(10); // Add 10 to the front
         deque.addFront(30); // Add 30 to the front
         deque.addFront(50); // Add 50 to the front
         
         String s = deque.toString()+"\n";
         // Remove rear
         deque.removeRear(); 
         s += deque.toString()+"\n";
         deque.removeRear(); 
         s += deque.toString()+"\n";
         deque.removeRear(); 
         s += deque.toString()+"\n";
        
         // add Rear
         deque.addRear(10); // Add 10 to the Rear
         deque.addRear(30); // Add 30 to the Rear
         deque.addRear(50); // Add 50 to the Rear
         
         // Remove front
         deque.removeFront(); 
         s += deque.toString()+"\n";
         deque.removeFront(); 
         s += deque.toString()+"\n";
         deque.removeFront(); 
         s += deque.toString()+"\n";
         
         
         assertTrue(TestsSupport.isCorrect("pubTest06.txt", s));
     }

     
     //*******Palindrome ******
     
     

     @Test
    public void testPalindrome() {
    	        // Standard palindrome
    	        assertTrue(Util.isPalindrome("racecar"));

    	        // Non-palindrome
    	        assertFalse(Util.isPalindrome("hello"));
    	        

    	        // Palindrome with spaces and special characters
    	        assertTrue(Util.isPalindrome("A man, a plan, a canal: Panama"));
    	    }
     
     
     
     
     
     
     
     //*******Sliding window ******
     
 

         @Test
         public void testSlidingWindowMaxIntegers() {
             // Test input
             Integer[] nums = {1, 3, -10, -2, 15, 5, 6, 17};
             int k = 3;
             
             // Expected output
             Integer[] expected = {3, 3, 15, 15, 15, 17};
             
             // Call the method 
            Integer[] result = Util.slidingWindowMax(nums, k); 
             
             // Assert the result matches the expected output
             assertArrayEquals(expected, result);
             
              nums = new Integer[]{1, 2, 5, -30, 15, 13, 2, 5};
              expected = new Integer[]{5, 5, 15, 15, 15, 13};
              // Assert the result matches the expected output
               result = Util.slidingWindowMax(nums, k); 
              assertArrayEquals(expected, result);
         }
    
        
     
	
}