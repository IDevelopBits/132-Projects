package tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import systemImp.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class StudentTests {
	
	// Test DoublyLinkedList remove() methods in single lists
	@Test
	public void testRemoveFirstLastOnSingleElements() {
		DoublyLinkedList<Integer> numberList = new DoublyLinkedList<>();
		numberList.addLast(3);
		numberList.removeFirst();
		numberList.addFirst(1856);
		numberList.removeLast();
	}
	
	// Test DoublyLinkedList add() and getSize() methods
    @Test
    public void testDoublyLinkedListAddSizeOperations() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addFirst(1);
        list.addLast(2);

        assertEquals(2, list.getSize()); 
        assertEquals(1, (int) list.getFirst());
        assertEquals(2, (int) list.getLast());
    }

    // Test DoublyLinkedList Iterator using a for each loop
    @Test
    public void testDoublyLinkedListIterator() {
        DoublyLinkedList<String> names = new DoublyLinkedList<>();
        names.addFirst("Jy'Mere");
        names.addLast("James");
        names.addFirst("Jamari");
        Iterator<String> iterator = names.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Jamari", iterator.next());
        
        iterator.remove();
        try {
        	iterator.remove();
        	// Remove can't be called twice in a row
        	fail("IllegalStateException should be thrown!");
        } catch (IllegalStateException e) {
        	assertTrue(true);
        }
    }

    // Ensure Stack push() and pop() methods work
    @Test
    public void testStackPushPop() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        
        assertEquals(20, (int) stack.pop());
        assertEquals(10, (int) stack.pop());
    }

    // Test Queue enqueue() and dequeue() methods
    @Test
    public void testQueueEnqueueDequeue() {
        Queue<String> queue = new Queue<>();
        queue.enqueue("Java");
        queue.enqueue("Python");
        queue.enqueue("Rust");
        
        assertEquals("Java", queue.dequeue()); // FIFO behavior
        assertEquals("Python", queue.dequeue());
    }

    // Test Deque add() and remove() methods for both sides
    @Test
    public void testDequeAddRemoveBothEnds() {
        Deque<Double> deque = new Deque<>();
        deque.addLast(6.9);
        deque.addFirst(3.14);
        
        assertTrue(3.14 == deque.removeFirst());
        assertTrue(6.9 == deque.removeLast());
    }

    // Ensure removing from empty structures thrown the right Exception
    @Test
    public void testRemovingFromEmptyStructure() {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new Queue<>();
        Deque<Integer> deque = new Deque<>();
        
        try {
        	stack.pop();
        	queue.dequeue();
        	deque.removeFirst();
        	fail("IllegalStateException should be thrown!");
        } catch (IllegalStateException e) {
        	// Do nothing the test passes
        }
    }

    // Ensure Queue is in FIFO order
    @Test
    public void testQueueOrdering() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        
        // Should be FIFO
        assertEquals(1, (int) queue.dequeue());
        assertEquals(2, (int) queue.dequeue());
        assertEquals(3, (int) queue.dequeue());
    }

    // Ensure Stack is in LIFO order
    @Test
    public void testStackOrdering() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        // Should be LIFO
        assertEquals(3, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
    }

    // Ensure Deque ordering works for addFirst() and addLast()
    @Test
    public void testDequeOrderingBothEnds() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        
        assertEquals(0, (int) deque.removeFirst());
        assertEquals(1, (int) deque.removeFirst());
        assertEquals(2, (int) deque.removeLast());
    }
    
    // Ensure Deque size() method returns correct size
    @Test
    public void testDequeSizeAfterOperations() {
        Deque<Double> deque = new Deque<>();
        deque.addFirst(1.1);
        deque.addLast(2.2);
        deque.addFirst(3.3);
        deque.removeFirst();
        assertEquals(2, deque.size());
    }
}