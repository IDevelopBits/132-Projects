package tests;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import systemImp.*;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PublicTests {
	
	@Test
    public void testHomogeneousList() {

		    // Create a StringBuilder to accumulate output
		    StringBuilder output = new StringBuilder();

		    // --- Homogeneous List: Only Strings ---
		    output.append("== Homogeneous List (String) Test ==\n");

		    // Create a new MyArrayList that only accepts Strings
		    MyArrayList stringList = new MyArrayList(String.class);

		    // Add some Strings to the list
		    output.append("Adding elements to the String list...\n");
		    stringList.add("Hello");
		    stringList.add("World");
		    stringList.add("Java");

		    // Try adding an Integer (should throw IllegalArgumentException)
		    try {
		        stringList.add(100);  // Invalid, should throw exception
		    } catch (IllegalArgumentException e) {
		        output.append("Error adding integer to String list: ").append(e.getMessage()).append("\n");
		    }

		    // Accessing elements by index
		    output.append("Accessing elements:\n");
		    try {
		        output.append("Element at index 0: ").append(stringList.get(0)).append("\n");  // Should print "Hello"
		        output.append("Element at index 1: ").append(stringList.get(1)).append("\n");  // Should print "World"
		        output.append("Element at index 2: ").append(stringList.get(2)).append("\n");  // Should print "Java"
		    } catch (Exception e) {
		        output.append("Error: ").append(e.getMessage()).append("\n");
		    }

		    // Checking the size of the list
		    output.append("Size of the list: ").append(stringList.size()).append("\n");  // Should be 3

		    // Removing an element by index
		    output.append("Removing element at index 1 (World)...\n");
		    stringList.remove(1);  // Should remove "World"
		    output.append(stringList).append("\n");  // Should print [Hello, Java]
		    output.append(stringList.printFullArray()).append("\n"); // Same as above with 8 nulls

		    // Attempting to remove a non-existent element (should throw IllegalArgumentException)
		    try {
		        stringList.remove("NonExistentElement");
		    } catch (Exception e) {
		        output.append("Error removing non-existent element: ").append(e.getMessage()).append("\n");
		    }

		    // Checking if the list contains a specific element
		    output.append("Checking if the list contains 'Hello': ").append(stringList.contains("Hello")).append("\n");  // true
		    output.append("Checking if the list contains 'World': ").append(stringList.contains("World")).append("\n");  // false

		    // Trimming the size of the internal array
		    output.append("Trimming to size...\n");
		    stringList.trimToSize();  // Reduces the internal array size to match the list size
		    output.append("Size after trim: ").append(stringList.size()).append("\n"); // Should be 2
		    output.append(stringList).append("\n");  // Should print [Hello, Java]
		    output.append(stringList.printFullArray()).append("\n"); // Same as above

		    // Creating a sublist
		    output.append("Creating a sublist from index 0 to 1...\n");
		    MyArrayList sublist = stringList.sublist(0, 1);
		    output.append(sublist).append("\n"); // Should print [Hello]

		    // Clearing the list
		    output.append("Clearing the list...\n");
		    stringList.clear();
		    output.append(stringList).append("\n"); // Should print an empty list []
		    output.append(stringList.printFullArray()).append("\n"); // Should print an empty list []
		
		    
		    String result =  output.toString();
	        assertTrue(TestsSupport.isCorrect("pubTest01.txt", result));

	}
	
	
	@Test
    public void testHeterogeneousList() {
	    // Create a StringBuilder to accumulate output
	    StringBuilder output = new StringBuilder();

	    // --- Heterogeneous List: Accepting Objects of Any Type ---
	    output.append("== Heterogeneous List (Object) Test ==\n");

	    // Create a new MyArrayList that can accept any type (Object)
	    MyArrayList objectList = new MyArrayList(Object.class);

	    // Adding various types of elements (String, Integer, Double, etc.)
	    output.append("Adding heterogeneous elements...\n");
	    objectList.add("Hello");  // String
	    objectList.add(100);  // Integer
	    objectList.add(3.14);  // Double
	    objectList.add(true);  // Boolean

	    output.append(objectList).append("\n"); // Should print [Hello, 100, 3.14, true]

	    // Removing an element by index
	    objectList.remove(2);  // Removes the Double value 3.14

	    // Adding elements from an array
	    output.append("Adding elements from an array...\n");
	    Object[] additionalElements = { "Java", 42, 2.718 };
	    objectList.addAll(additionalElements);
	    output.append(objectList.printFullArray()).append("\n");

	    // Creating a sublist from index 1 to 4
	    output.append("Creating a sublist from index 1 to 4...\n");
	    MyArrayList sublistObject = objectList.sublist(1, 4);
	    output.append(sublistObject).append("\n");  // Should print [100, true, Java]

	    String result =  output.toString();
        assertTrue(TestsSupport.isCorrect("pubTest02.txt", result));
	}

	@Test
    public void testPolymorphicList() {
	    // Create a StringBuilder to accumulate output
	    StringBuilder output = new StringBuilder();

	    // --- Polymorphic List: Accepting Base and subclasses ---
	    output.append("== Polymorphic List Test ==\n");

	    // Create a new MyArrayList that can accept any Person
	    MyArrayList objectList = new MyArrayList(Person.class);

	    // Adding various Persons
	    output.append("Adding Person elements...\n");
	    objectList.add(new Person("Joe", 123));  
	    objectList.add(new Student("Joe", 456, 2025, 3.5));  
	    objectList.add(new Person("Mark", 789)); 
	    objectList.add(new Student("Jen", 135, 2024, 3.6)); 

	    output.append(objectList).append("\n");

	    // Removing an element by index
	    objectList.remove(2);  // Remove the third element (Person "Mark")

	    // Adding elements from an array
	    output.append("Adding an element from an array...\n");
	    Object[] additionalElements = { new Person("Jeff", 246) };
	    objectList.addAll(additionalElements);
	    output.append(objectList).append("\n");

	    // Attempt to remove a Student by object (should work due to `equals` method)
	    objectList.remove(new Student("Jeff", 246, 2022, 3.0));   // Should work because of overridden `equals` method

	    // Attempt to remove a Person by object (should not work without matching fields)
	    try {
	        objectList.remove(new Person("Jen", 135));  // Should fail 
	    } catch (Exception e) {
	        output.append("Error removing non-existent element: ").append(e.getMessage()).append("\n");
	    }
	    output.append(objectList).append("\n");

	    String result =  output.toString();
	    System.out.println(result);
        assertTrue(TestsSupport.isCorrect("pubTest03.txt", result));
	}

	@Test
	public void testAllStackOperationsWithIntegers() {
	    // Create a StringBuilder to accumulate the test output
	    StringBuilder output = new StringBuilder();

	    // Initialize MyStack with Integer type
	    MyStack stack = new MyStack(Integer.class);
	    
	    // Test the size after initialization (should be 0)
	    output.append("Initial size (should be 0): ").append(stack.size()).append("\n");

	    // Push elements onto the stack
	    stack.push(10);
	    stack.push(20);
	    stack.push(30);

	    // Test size after pushes (should be 3)
	    output.append("Size after pushing elements (should be 3): ").append(stack.size()).append("\n");

	    // Test the toString method
	    output.append("Stack content (should be 'Stack: [10, 20, 30]'): ").append(stack.toString()).append("\n");

	    // Peek the top element (should be 30)
	    output.append("Peek top element (should be 30): ").append(stack.peek()).append("\n");

	    // Pop an element (should be 30)
	    Integer poppedElement = (Integer) stack.pop();
	    output.append("Popped element (should be 30): ").append(poppedElement).append("\n");

	    // Test the size after popping (should be 2)
	    output.append("Size after popping an element (should be 2): ").append(stack.size()).append("\n");

	    // Peek the new top element (should be 20)
	    output.append("Peek top element (should be 20): ").append(stack.peek()).append("\n");

	    // Test popping all elements
	    poppedElement = (Integer) stack.pop();
	    output.append("Popped element (should be 20): ").append(poppedElement).append("\n");

	    poppedElement = (Integer) stack.pop();
	    output.append("Popped element (should be 10): ").append(poppedElement).append("\n");

	    // Test size after popping all elements (should be 0)
	    output.append("Size after popping all elements (should be 0): ").append(stack.size()).append("\n");

	    // Test isEmpty method (should be true)
	    output.append("Is stack empty (should be true): ").append(stack.isEmpty()).append("\n");

	    // Test the exception when popping from an empty stack
	    try {
	        stack.pop();
	    } catch (IllegalStateException e) {
	        output.append("Exception when popping from empty stack (message): ").append(e.getMessage()).append("\n");
	    }

	    // Test the exception when peeking from an empty stack
	    try {
	        stack.peek();
	    } catch (IllegalStateException e) {
	        output.append("Exception when peeking from empty stack (message): ").append(e.getMessage()).append("\n");
	    }

	    // Clear the stack (although it should already be empty)
	    stack.clear();
	    output.append("Size after clear (should be 0): ").append(stack.size()).append("\n");

	    // Convert StringBuilder to string and pass it to isCorrect
	    String result = output.toString();
	    System.out.println(result);
	    assertTrue(TestsSupport.isCorrect("pubTest04.txt", result));
	}

	@Test
	public void testElementType() {
	    // Create a stack for Integer type
		
		 // Test that an IllegalArgumentException is thrown when creating a stack with null type
	    assertThrows(IllegalArgumentException.class, () -> {
	        MyStack badStack = new MyStack(null);  // This should throw IllegalArgumentException
	    });
		
		
	    MyStack stack = new MyStack(Integer.class);

	    // Call elementType method and check if it returns Integer.class
	    Class<?> expectedType = Integer.class;
	    Class<?> actualType = stack.elementType();

	    // Assert that the element type of the stack is Integer
	    assertEquals("The element type should be Integer", expectedType, actualType);
	    
	    // Now create a stack for String type
	    MyStack stringStack = new MyStack(String.class);
	    
	    // Call elementType method and check if it returns String.class
	    expectedType = String.class;
	    actualType = stringStack.elementType();
	    
	    // Assert that the element type of the stack is String
	    assertEquals("The element type should be String", expectedType, actualType);
	}	
}